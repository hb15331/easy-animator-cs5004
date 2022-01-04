package cs5004.animator.view.svg;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cs5004.animator.model.Animation;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationType;
import cs5004.animator.model.ColorChange;
import cs5004.animator.model.Move;
import cs5004.animator.model.Resize;
import cs5004.animator.model.Shape;
import cs5004.animator.model.ShapeType;
import cs5004.animator.view.AnimationViewInterface;

/**
 * Creates an SVG representation of the animation.
 */
public class SVGView implements AnimationViewInterface {
  private Appendable out;
  private int speed;

  /**
   * Creates an SVG file that can be opened in a web browser for a visual representation of the
   * animation.
   *
   * @param model the model that contains all the animation data.
   * @param speed the speed of the animation in ticks per second
   * @param out the output destination.  Defaults to System.out, but should be set to a .svg file.
   * @throws IOException if output destination cannot be written to.
   * @throws IllegalArgumentException if the speed is not positive
   */
  @Override
  public void render(AnimationModel model, int speed, Appendable out)
          throws IOException, IllegalArgumentException {
    if (speed <= 0) {
      throw new IllegalArgumentException("Invalid speed value");
    }

    this.out = out;
    this.speed = speed;
    List<Animation> animations = model.getAnimations();

    // svg header
    out.append("<svg viewBox=\"" + model.getLeft() + " " + model.getTop() + " " + model.getWidth()
            + " " + model.getHeight()
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n");


    for (Shape s : model.getShapes()) {
      initializeShape(s);

      // set visibility for shapes that aren't there at the beginning of the animation.
      if (s.getAppear() > 1) {
        out.append(" visibility=\"hidden\" >\n  <set attributeName=\"visibility\" to=\"visible\" "
                + "begin=\"" + s.getAppear() * 1000 / speed + "ms\" fill=\"freeze\"/>\n");
      } else {
        out.append(" >\n");
      }

      // create animations for the current shape
      for (Animation a : animations) {
        if (a.getShape().getName().equals(s.getName())) {
          renderAnimation(a);
        }
      }

      // hide shape if it disappears before end of animation
      if (s.getDisappear() < model.getModelEndTime()) {
        out.append(" <set attributeName=\"visibility\" to=\"hidden\" begin=\""
                + s.getDisappear() * 1000 / speed + "ms\" fill=\"freeze\"/>\n");
      }

      // close shape
      if (s.getType() == ShapeType.OVAL) {
        out.append("</ellipse>\n\n");
      } else {
        out.append("</rect>\n\n");
      }
    }

    out.append("</svg>\n");

    if (out instanceof FileWriter) {
      ((FileWriter)out).close();
    }
  }

  private void initializeShape(Shape s) throws IOException {
    if (s.getType() == ShapeType.OVAL) {
      addEllipse(s);
    } else {
      addRectangle(s);
    }
  }

  private void addEllipse(Shape s) throws IOException {
    out.append("<ellipse id=\"" + s.getName() + "\" cx=\""
            + (s.getLocation().getX() + s.getHorizontal() / 2)
            + "\" cy=\"" + (s.getLocation().getY() + s.getVertical() / 2) + "\" rx=\""
            + s.getHorizontal() / 2 + "\" ry=\"" + s.getVertical() / 2
            + "\" fill=\"rgb" + s.printColor() + "\"");
  }

  private void addRectangle(Shape s) throws IOException {
    out.append("<rect id=\"" + s.getName() + "\" x=\"" + s.getLocation().getX()
            + "\" y=\"" + s.getLocation().getY() + "\" width=\""
            + s.getHorizontal() + "\" height=\"" + s.getVertical()
            + "\" fill=\"rgb" + s.printColor() + "\"");
  }


  private void renderAnimation(Animation a) throws IOException {
    if (a.getType() == AnimationType.COLOR_CHANGE) {
      renderColor(a);
    } else if (a.getType() == AnimationType.MOVE) {
      renderMove(a);
    } else {
      renderResize(a);
    }
  }

  private void renderColor(Animation a) throws IOException {
    out.append("  <animate attributeType=\"CSS\" attributeName=\"fill\" from=\"rgb"
            + ((ColorChange)a).printStartColor()
            + "\" to=\"rgb" + ((ColorChange) a).printEndColor() + "\" begin=\""
            + a.getStart() * 1000 / speed + "ms\" dur=\""
            + (a.getEnd() - a.getStart()) * 1000 / speed
            + "ms\"" + " fill=\"freeze\" />\n");
  }

  private void renderMove(Animation a) throws IOException {
    if (a.getShape().getType() == ShapeType.OVAL) {
      if (((Move) a).getStartX() != ((Move) a).getEndX()) {
        out.append("  <animate attributeType=\"xml\" begin=\"" + a.getStart() * 1000 / speed
                + "ms\" dur=\"" + (a.getEnd() - a.getStart()) * 1000 / speed
                + "ms\" attributeName=\"cx\" from=\""
                + (((Move) a).getStartX() + a.getShape().getHorizontal() / 2) + "\" to=\""
                + (((Move) a).getEndX() + a.getShape().getHorizontal() / 2)
                + "\" fill=\"freeze\" />\n");
      }
      if (((Move) a).getStartY() != ((Move) a).getEndY()) {
        out.append("  <animate attributeType=\"xml\" begin=\"" + a.getStart() * 1000 / speed
                + "ms\" dur=\"" + (a.getEnd() - a.getStart()) * 1000 / speed
                + "ms\" attributeName=\"cy\" from=\""
                + (((Move) a).getStartY() + a.getShape().getVertical() / 2) + "\" to=\""
                + (((Move) a).getEndY() + a.getShape().getVertical() / 2)
                + "\" fill=\"freeze\" />\n");
      }
    } else {  // Rectangle Move
      if (((Move) a).getStartX() != ((Move) a).getEndX()) {
        out.append("  <animate attributeType=\"xml\" begin=\"" + a.getStart() * 1000 / speed
              + "ms\" dur=\"" + (a.getEnd() - a.getStart()) * 1000 / speed
              + "ms\" attributeName=\"x\" from=\"" + ((Move) a).getStartX() + "\" to=\""
              + ((Move) a).getEndX() + "\" fill=\"freeze\" />\n");
      }
      if (((Move) a).getStartY() != ((Move) a).getEndY()) {
        out.append("  <animate attributeType=\"xml\" begin=\"" + a.getStart() * 1000 / speed
                + "ms\" dur=\"" + (a.getEnd() - a.getStart()) * 1000 / speed
                + "ms\" attributeName=\"y\" from=\"" + ((Move) a).getStartY() + "\" to=\""
                + ((Move) a).getEndY() + "\" fill=\"freeze\" />\n");
      }
    }
  }

  private void renderResize(Animation a) throws IOException {
    if (a.getShape().getType() == ShapeType.OVAL) {
      if (((Resize) a).getStartWidth() != ((Resize) a).getEndWidth()) {
        out.append("  <animate attributeType=\"xml\" begin=\"" + a.getStart() * 1000 / speed
                + "ms\" dur=\"" + (a.getEnd() - a.getStart()) * 1000 / speed
                + "ms\" attributeName=\"rx\" from=\"" + ((Resize) a).getStartWidth() / 2
                + "\" to=\"" + ((Resize) a).getEndWidth() / 2 + "\" fill=\"freeze\" />\n");
      }
      if (((Resize) a).getStartHeight() != ((Resize) a).getEndHeight()) {
        out.append("  <animate attributeType=\"xml\" begin=\"" + a.getStart() * 1000 / speed
                + "ms\" dur=\"" + (a.getEnd() - a.getStart()) * 1000 / speed
                + "ms\" attributeName=\"ry\" from=\"" + ((Resize) a).getStartHeight() / 2
                + "\" to=\"" + ((Resize) a).getEndHeight() / 2 + "\" fill=\"freeze\" />\n");
      }
    } else {  // Rectangle Resize
      if (((Resize) a).getStartWidth() != ((Resize) a).getEndWidth()) {
        out.append("  <animate attributeType=\"xml\" begin=\"" + a.getStart() * 1000 / speed
                + "ms\" dur=\"" + (a.getEnd() - a.getStart()) * 1000 / speed
                + "ms\" attributeName=\"width\" from=\"" + ((Resize) a).getStartWidth() + "\" to=\""
                + ((Resize) a).getEndWidth() + "\" fill=\"freeze\" />\n");
      }
      if (((Resize) a).getStartHeight() != ((Resize) a).getEndHeight()) {
        out.append("  <animate attributeType=\"xml\" begin=\"" + a.getStart() * 1000 / speed
              + "ms\" dur=\"" + (a.getEnd() - a.getStart()) * 1000 / speed
              + "ms\" attributeName=\"height\" from=\"" + ((Resize) a).getStartHeight() + "\" to=\""
              + ((Resize) a).getEndHeight() + "\" fill=\"freeze\" />\n");
      }
    }
  }
}
