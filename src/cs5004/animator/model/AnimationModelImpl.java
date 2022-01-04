package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the actual Model of the animation. It contains all the data and
 * functionality to be used by controller and view.
 */

public class AnimationModelImpl implements AnimationModel {
  private ArrayList<Shape> shapes;
  private ArrayList<Animation> animations;
  private int currentFrame;
  private int endTime;
  private int canvasTop;
  private int canvasLeft;
  private int canvasHeight;
  private int canvasWidth;

  private AnimationModel currentState;


  /**
   * Construct an AnimationModelImpl object. It creates two empty arrays that are used to store the
   * shapes and actions respectively.
   */
  public AnimationModelImpl() {
    shapes = new ArrayList<>();
    animations = new ArrayList<>();
    currentFrame = -1;
  }

  @Override
  public void addShape(Shape newShape) throws IllegalArgumentException {
    for (Shape s : shapes) {
      if (s.getName().equalsIgnoreCase(newShape.getName())) {
        throw new IllegalArgumentException("New Shape name already exists.");
      }
    }
    shapes.add(newShape);
  }

  @Override
  public void addAnimation(Animation newAnimation) throws IllegalArgumentException {
    // check for conflicts
    for (Animation a : animations) {
      if (a.getShape().getName().equals(newAnimation.getShape().getName())
              && a.getType() == newAnimation.getType()
              && !(a.getEnd() <= newAnimation.getStart()
              || newAnimation.getEnd() <= a.getStart())) {
        throw new IllegalArgumentException("This animation conflicts with an existing animation.");
      }
    }

    animations.add(newAnimation);

    if (newAnimation.getEnd() > endTime) {
      endTime = newAnimation.getEnd();
    }
  }

  @Override
  public void nextFrame() {
    currentFrame++;
    for (Animation a : animations) {
      a.modifyShape(currentFrame);
    }
  }

  @Override
  public List<Shape> getShapes() {
    return shapes;
  }


  @Override
  public List<Animation> getAnimations() {
    return animations;
  }


  @Override
  public String declarativeAnimation() throws IllegalStateException {
    StringBuilder string = new StringBuilder();

    // sort the list by startTime for final print.  might be backwards
    Collections.sort(animations, (a1, a2) -> a1.getStart() - a2.getStart());

    for (Animation a : animations) {
      string.append(a.toString());
    }
    return string.toString();
  }

  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();
    string.append("Shapes:\n");
    for (Shape s : shapes) {
      string.append(s.toString());
    }

    return string.toString();
  }

  @Override
  public void setBounds(int x, int y, int height, int width) {
    canvasTop = y;
    canvasLeft = x;
    canvasHeight = height;
    canvasWidth = width;
  }

  @Override
  public int getTop() {
    return canvasTop;
  }

  @Override
  public int getLeft() {
    return canvasLeft;
  }

  @Override
  public int getHeight() {
    return canvasHeight;
  }

  @Override
  public int getWidth() {
    return canvasWidth;
  }

  @Override
  public void setModelEndTime(int endTime) {
    this.endTime = endTime;
  }

  @Override
  public int getModelEndTime() {
    return endTime;
  }

  @Override
  public void reset() {
    for (Shape s : shapes) {
      s.reset();
    }
    currentFrame = 0;
  }

  @Override
  public int getCurrentFrame() {
    return currentFrame;
  }
}
