package cs5004.animator.model;

/**
 * The abstract class implements the Animation interface. It implements
 * the common operations of its Animation subclasses.
 */

public abstract class AbstractAnimation implements Animation {
  Shape s;
  int startTime;
  int endTime;

  AbstractAnimation(Shape s, int startTime, int endTime) throws IllegalArgumentException {
    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Times must be non-negative.");
    }
    if (startTime >= endTime) {
      throw new IllegalArgumentException("End time must be after start time.");
    }
    this.s = s;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public Shape getShape() {
    return s;
  }

  @Override
  public int getStart() {
    return startTime;
  }

  @Override
  public int getEnd() {
    return endTime;
  }

  @Override
  public void setStart(int startTime) {
    this.startTime = startTime;
  }

  @Override
  public void setEnd(int endTime) {
    this.endTime = endTime;
  }
}
