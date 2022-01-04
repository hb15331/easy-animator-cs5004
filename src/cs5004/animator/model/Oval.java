package cs5004.animator.model;

/**
 * A class that represents an Oval shape.
 */
public class Oval extends AbstractShape {

  /**
   * Initializes an Oval with its name, position, dimensions, and duration of visibility.
   *
   * @param name the name of the shape.  Must be unique.
   * @throws IllegalArgumentException if radii, appear, or disappear are negative
   *         or if disappear < appear
   *
   */
  public Oval(String name) throws IllegalArgumentException {
    super(name);
  }

  @Override
  public String toString() {
    return "Name: " + getName() + "\nType: oval\n"
            + "Center: " + getLocation() + " X radius: " + getHorizontal()
            + " Y radius: " + getVertical()
            + " Color: " + printColor()
            + "\nAppears at t = " + appearTime
            + "\nDisappears at t = " + disappearTime + "\n\n";
  }

  @Override
  public ShapeType getType() {
    return ShapeType.OVAL;
  }

}

