package cs5004.animator.model;

/**
 * A type of Animation that gradually changes the dimensions of a given shape.
 * Given a target height and width, this class calculates a linear progression of values that
 * gradually alters the Shapes dimensions from its original height/width to the target values.
 */
public class Resize extends AbstractAnimation {
  private double speedWidth;
  private double speedHeight;

  private double startWidth;
  private double startHeight;
  private double endWidth;
  private double endHeight;

  /**
   * Initializes a Resize animation with its target shape, target size, and time interval.
   *
   * @param s the Shape which will be changing color.
   * @param startTime the time at which the animation will begin.
   * @param endTime the time at which the animation will end.
   * @param startHeight the height of the shape at the beginning of the animation.
   * @param startWidth the width of the shape at the beginning of the animation.
   * @param endHeight the final height of the shape at the end of the animation.
   * @param endWidth the final width of the shape at the end of the animation.
   * @throws IllegalArgumentException if start or end times are negative, or if start is after end.
   */
  public Resize(Shape s, int startTime, int endTime, double startHeight, double startWidth,
                double endHeight, double endWidth) throws IllegalArgumentException {
    super(s, startTime, endTime);
    this.startHeight = startHeight;
    this.startWidth = startWidth;
    this.endWidth = endWidth;
    this.endHeight = endHeight;
    speedWidth = (endWidth - startWidth) / (endTime - startTime);
    speedHeight = (endHeight - startHeight) / (endTime - startTime);
  }

  @Override
  public void modifyShape(int time) {
    if (time > startTime && time <= endTime) {
      double width = startWidth + speedWidth * (time - startTime);
      double height = startHeight + speedHeight * (time - startTime);
      s.setHorizontal(width);
      s.setVertical(height);
    }
  }

  @Override
  public String toString() {
    return "Shape " + s.getName() + " scales from Width: " + startWidth
            + " Height: " + startHeight + " to Width: " + endWidth
            + " Height: " + endHeight + " from t=" + startTime + " to t=" + endTime + "\n";
  }

  @Override
  public AnimationType getType() {
    return AnimationType.RESIZE;
  }

  /**
   * Returns the width of the Shape at the beginning of the animation.
   *
   * @return the width of the Shape at the beginning of the animation.
   */
  public double getStartWidth() {
    return startWidth;
  }

  /**
   * Returns the height of the Shape at the beginning of the animation.
   *
   * @return the height of the Shape at the beginning of the animation.
   */
  public double getStartHeight() {
    return startHeight;
  }

  /**
   * Returns the width of the Shape at the end of the animation.
   *
   * @return the width of the Shape at the end of the animation.
   */
  public double getEndWidth() {
    return endWidth;
  }

  /**
   * Returns the height of the Shape at the end of the animation.
   *
   * @return the height of the Shape at the end of the animation.
   */
  public double getEndHeight() {
    return endHeight;
  }

}

