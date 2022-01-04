package cs5004.animator.model;

import java.util.List;

/**
 * An interface that provides for methods needed to create a full animation.  This Model allows a
 * user to create an animation that contains multiple shapes that can each be animated in more than
 * one way.  
 */
public interface AnimationModel {
  /**
   * Add shapes to the shape array.
   *
   * @param newShape the specified shape to be added to the array
   * @throws IllegalArgumentException if the shape to be added already exists in the array
   */
  void addShape(Shape newShape);

  /**
   * Add animations to the animation array.
   *
   * @param newAnimation the specified animation to be added
   * @throws IllegalArgumentException before the new animation ends, an existing animation already
   *                                  starts, or the new animation starts before an existing
   *                                  animation ends.
   */
  void addAnimation(Animation newAnimation);

  /**
   * Advances the state of the animation by a single frame.
   */
  void nextFrame();

  /**
   * Return the shape array in this model.
   *
   * @return the shape array in this model
   */
  List<Shape> getShapes();

  /**
   * Returns a List of animations in this model.
   *
   * @return a List of animations in this model.
   */
  List<Animation> getAnimations();

  /**
   * Returns a String description of the animations.
   * Must be run after all the animations are completed.
   *
   * @return a String description of the animations.
   * @throws IllegalStateException if the animation is not complete when called.
   */
  String declarativeAnimation() throws IllegalStateException;

  /**
   * Returns a String representation current state of all of the Shapes.
   *
   * @return a String representation current state of all of the Shapes.
   */
  @Override
  String toString();

  /**
   * Sets the bounds for the animation window.
   *
   * @param x horizontal offset
   * @param y vertical offset
   * @param height window height
   * @param width window width
   */
  void setBounds(int x, int y, int height, int width);

  /**
   * Returns the top of the window.
   * @return the top of the window.
   */
  int getTop();

  /**
   * Returns the left of the window.
   * @return the left of the window.
   */
  int getLeft();

  /**
   * Returns the height of the window.
   * @return the height of the window.
   */
  int getHeight();

  /**
   * Returns the width of the window.
   * @return the width of the window.
   */
  int getWidth();

  /**
   * Sets the time at which the animation ends.
   * @param endTime  the time at which the animation ends.
   */
  void setModelEndTime(int endTime);

  /**
   * Returns the time at which the animation ends.
   * @return the time at which the animation ends.
   */
  int getModelEndTime();

  /**
   * Resets each Shape to its original configuration and the currentFrame to 0.
   */
  void reset();

  /**
   * Returns the current frame of the animation.
   * @return the current frame of the animation.
   */
  int getCurrentFrame();
}
