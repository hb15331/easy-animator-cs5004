package cs5004.animator.model;

/**
 * This class represents a 2D point. This point is denoted in Cartesian
 * coordinates as (x,y).
 */
public class Point2D {
  private double x;
  private double y;

  /**
   * Construct a 2d point with the given coordinates.
   *
   * @param x the x-coordinate of this point
   * @param y the y-coordinate of this point
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Return the x-coordinate of this point.
   *
   * @return x-coordinate of this point.
   */
  public double getX() {
    return x;
  }

  /**
   * Sets the x-coordinate of this point.
   *
   * @param x x-coordinate of this point.
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Return the y-coordinate of this point.
   *
   * @return y-coordinate of this point.
   */
  public double getY() {
    return y;
  }

  /**
   * Sets the y-coordinate of this point.
   * @param y y-coordinate of this point.
   */
  public void setY(double y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }
}