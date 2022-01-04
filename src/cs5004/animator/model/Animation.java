package cs5004.animator.model;

/**
 * An interface that describes methods needed to gradually mutate a shape in a way that creates an
 * animation.  The methods can be used to animate an attribute of the shape that can be quantified
 * using a continuous variable, such as size, location, and color.
 */
public interface Animation {

  /**
   * This method sets a parameter of the target shape to its appropriate value according to the time
   * of the frame.
   * If the time is outside the time interval of the Animation, this method does nothing.
   * Example: If changing a size from 0 to 100 over a time interval of 0 to 5, modifyShape(1) will
   * set the size to 20.  modifyShape(3) will set the size to 60.  modifyShape(6) will do nothing.
   * modifyShape is intended to be called for each time value of the animation (in order).
   * The animation is initialized when modifyShape is called for the startTime of the animation.  If
   * this call is not made, the animation will not work.
   * @param time a specific time in animation
   */
  void modifyShape(int time);

  /**
   * Returns the Shape that is acted upon by the Animation.
   *
   * @return the Shape that is acted upon by the Animation.
   */
  Shape getShape();

  /**
   * Returns the type of the Animation. Move, Resize, or Color Change
   *
   * @return the type of the Animation.
   */
  AnimationType getType();

  /**
   * Returns the time at which the Animation begins.
   *
   * @return the time at which the Animation begins.
   */
  int getStart();

  /**
   * Returns the time at which the Animation ends.
   *
   * @return the time at which the Animation ends.
   */
  int getEnd();

  /**
   * Sets the time at which the Animation begins.
   *
   * @param startTime the time at which the Animation begins.
   */
  void setStart(int startTime);

  /**
   * Sets the time at which the Animation ends.
   *
   * @param endTime the time at which the Animation ends.
   */
  void setEnd(int endTime);

}
