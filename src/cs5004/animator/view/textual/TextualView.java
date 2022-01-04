package cs5004.animator.view.textual;

import java.io.FileWriter;
import java.io.IOException;

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
 * Creates a textual view of the animation described by the model.  Outputs a list of shapes and
 * their starting configurations, the times at which the shapes will be visible, and a list of
 * animations performed on the shapes in chronological order.
 */
public class TextualView implements AnimationViewInterface {
  /**
   * Creates the textual representation of the model.
   *
   * @param model the model that contains all the animation data.
   * @param speed the speed of the animation in ticks per second
   * @param out   the output destination.  Defaults to System.out, but can be set to a data file.
   * @throws IOException              if output destination cannot be written to.
   * @throws IllegalArgumentException if the speed is not positive
   */
  @Override
  public void render(AnimationModel model, int speed, Appendable out)
          throws IOException, IllegalArgumentException {
    if (speed <= 0) {
      throw new IllegalArgumentException("Invalid speed value");
    }

    // Create shapes
    for (Shape s : model.getShapes()) {
      if (s.getType() == ShapeType.OVAL) {
        out.append("Create oval " + s.getName() + " with center at ("
                + (s.getLocation().getX() + s.getHorizontal() / 2) + ","
                + (s.getLocation().getY() + s.getVertical() / 2) + "), X radius "
                + s.getHorizontal() / 2 + " Y radius " + s.getVertical() / 2 + ", rgb color "
                + s.printColor() + "\n");
      } else {
        out.append("Create rectangle " + s.getName() + " with corner at " + s.getLocation()
                + " width " + s.getHorizontal()
                + ", height " + s.getVertical()
                + ", rgb color " + s.printColor() + "\n");
      }
    }

    out.append("\n");

    // List visibility
    for (Shape s : model.getShapes()) {
      out.append(s.getName() + " appears at time t=" + s.getAppear()
              + " and disappears at time t=" + s.getDisappear() + "\n");
    }

    out.append("\n");

    // Display animations
    for (Animation a : model.getAnimations()) {
      if (a.getType() == AnimationType.MOVE) {
        if (a.getShape().getType() == ShapeType.OVAL) {
          out.append(a.getShape().getName() + " moves from ("
                  + (((Move) a).getStartX() + a.getShape().getHorizontal() / 2) + ","
                  + (((Move) a).getStartY() + a.getShape().getHorizontal() / 2) + ") to ("
                  + ((Move) a).getEndX() + "," + ((Move) a).getEndY() + ") from time t="
                  + a.getStart() + " to time t=" + a.getEnd() + "\n");
        } else {
          out.append(a.getShape().getName() + " moves from (" + ((Move) a).getStartX() + ","
                  + ((Move) a).getStartY() + ") to (" + ((Move) a).getEndX() + ","
                  + ((Move) a).getEndY() + ") from time t=" + a.getStart()
                  + " to time t=" + a.getEnd() + "\n");
        }

      } else if (a.getType() == AnimationType.RESIZE) {
        if (a.getShape().getType() == ShapeType.OVAL) {
          out.append(a.getShape().getName() + " scales from Width: "
                  + ((Resize) a).getStartWidth() / 2 + " Height: "
                  + ((Resize) a).getStartHeight() / 2 + " to Width: "
                  + ((Resize) a).getEndWidth() / 2 + " Height: " + ((Resize) a).getEndHeight() / 2
                  + " from t=" + a.getStart() + " to t=" + a.getEnd() + "\n");
        } else {
          out.append(a.getShape().getName() + " scales from Width: " + ((Resize) a).getStartWidth()
                  + " Height: " + ((Resize) a).getStartHeight() + " to Width: "
                  + ((Resize) a).getEndWidth() + " Height: " + ((Resize) a).getEndHeight()
                  + " from t=" + a.getStart() + " to t=" + a.getEnd() + "\n");
        }
      } else {
        out.append(a.getShape().getName() + " changes color from "
                + ((ColorChange) a).printStartColor() + " to " + ((ColorChange) a).printEndColor()
                + " from t=" + a.getStart() + " to t=" + a.getEnd() + "\n");
      }
    }

    if (out instanceof FileWriter) {
      ((FileWriter) out).close();
    }
  }
}
