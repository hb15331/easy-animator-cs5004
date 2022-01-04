package cs5004.animator.model;

/**
 * A type of Animation that gradually changes the position of a given shape.
 * Given a target position, this class calculates a linear progression of values that
 * gradually alters the Shapes position from its original location to the target position.
 */
public class Move extends AbstractAnimation {
  private Point2D start;
  private Point2D end;
  private double speedX;
  private double speedY;

  /**
   * Initializes a Move animation with its target shape, target position, and time interval.
   *
   * @param s the Shape which will be changing color.
   * @param startTime the time at which the animation will begin.
   * @param endTime the time at which the animation will end.
   * @param start the position of the shape at the start of the animation
   * @param end the position of the shape at the end of the animation
   * @throws IllegalArgumentException if start or end times are negative, or if start is after end.
   */
  public Move(Shape s, int startTime, int endTime, Point2D start, Point2D end)
          throws IllegalArgumentException {
    super(s, startTime, endTime);
    this.start = start;
    this.end = end;
    speedX = (end.getX() - start.getX()) / (endTime - startTime);
    speedY = (end.getY() - start.getY()) / (endTime - startTime);
  }

  @Override
  public void modifyShape(int time) {
    if (time > startTime && time <= endTime) {
      double x = start.getX() + (time - startTime) * speedX;
      double y = start.getY() + (time - startTime) * speedY;
      s.setLocation(new Point2D(x, y));
    }
  }

  @Override
  public String toString() {
    return "Shape " + s.getName() + " moves from " + start + " to " + end
            + " from t=" + startTime + " to t=" + endTime + "\n";
  }

  @Override
  public AnimationType getType() {
    return AnimationType.MOVE;
  }

  /**
   * Returns the x value of the position at the start of the animation.
   *
   * @return the x value of the position at the start of the animation.
   */
  public double getStartX() {
    return start.getX();
  }

  /**
   * Returns the y value of the position at the start of the animation.
   *
   * @return the y value of the position at the start of the animation.
   */
  public double getStartY() {
    return start.getY();
  }

  /**
   * Returns the x value of the position at the end of the animation.
   *
   * @return the x value of the position at the end of the animation.
   */
  public double getEndX() {
    return end.getX();
  }

  /**
   * Returns the y value of the position at the end of the animation.
   *
   * @return the y value of the position at the end of the animation.
   */
  public double getEndY() {
    return end.getY();
  }
}
