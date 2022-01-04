package cs5004.animator.model;

/**
 * A class that represents an Rectangle shape.
 */
public class Rectangle extends AbstractShape {
  /**
   * Initializes an Rectangle with its name, position, dimensions, and duration of visibility.
   *
   * @param name the name of the shape.  Must be unique.
   * @throws IllegalArgumentException if radii, appear, or disappear are negative
   *         or if disappear < appear
   */
  public Rectangle(String name) throws IllegalArgumentException {
    super(name);
  }

  @Override
  public String toString() {
    return "Name: " + getName() + "\nType: rectangle\n"
            + "Min corner: " + getLocation() + " Width: " + getHorizontal()
            + " Height: " + getVertical()
            + " Color: " + printColor()
            + "\nAppears at t = " + appearTime
            + "\nDisappears at t = " + disappearTime + "\n\n";
  }

  @Override
  public ShapeType getType() {
    return ShapeType.RECTANGLE;
  }

}









