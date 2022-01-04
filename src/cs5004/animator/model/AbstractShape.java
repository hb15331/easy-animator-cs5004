package cs5004.animator.model;

import java.awt.Color;

/**
 * An abstract class that implements common elements shared by Shape objects.
 */
public abstract class AbstractShape implements Shape {
  private String name;
  private Point2D location;
  private boolean visibility;
  private Color color;
  private double horizontal;
  private double vertical;

  private Point2D startingLocation;
  private double startingHorizontal;
  private double startingVertical;
  private Color startingColor;

  int appearTime;
  int disappearTime;

  AbstractShape(String name) throws IllegalArgumentException {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setLocation(Point2D p) {
    if (startingLocation == null) {
      startingLocation = p;
    }
    location = p;
  }

  @Override
  public Point2D getLocation() {
    return location;
  }

  @Override
  public void setVisibility(boolean visibility) {
    this.visibility = visibility;
  }

  @Override
  public boolean getVisibility() {
    return visibility;
  }

  @Override
  public void setColor(Color color) {
    if (startingColor == null) {
      startingColor = color;
    }
    this.color = color;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public void setVertical(double vertical) throws IllegalArgumentException {
    if (vertical < 0) {
      throw new IllegalArgumentException("The Y dimension must be set non-negative.");
    }
    if (startingColor == null) {
      startingVertical = vertical;
    }

    this.vertical = vertical;
  }

  @Override
  public double getVertical() {
    return vertical;
  }

  @Override
  public void setHorizontal(double horizontal) throws IllegalArgumentException {
    if (horizontal < 0) {
      throw new IllegalArgumentException("The X dimension must be set non-negative.");
    }
    if (startingColor == null) {
      startingHorizontal = horizontal;
    }
    this.horizontal = horizontal;
  }

  @Override
  public double getHorizontal() {
    return horizontal;
  }

  @Override
  public String printColor() {
    return String.format("(%d, %d, %d)", getColor().getRed(), getColor().getGreen(),
            getColor().getBlue());
  }

  @Override
  public int getAppear() {
    return appearTime;
  }

  @Override
  public int getDisappear() {
    return disappearTime;
  }

  @Override
  public void setAppear(int startTime) throws IllegalArgumentException {
    if (startTime < 0) {
      throw new IllegalArgumentException("Shape must appear and disappear at non-negative time.");
    }
    appearTime = startTime;
  }

  @Override
  public void setDisappear(int endTime) throws IllegalArgumentException {
    if (endTime < 0) {
      throw new IllegalArgumentException("Shape must appear and disappear at non-negative time.");
    }
    if (appearTime > endTime) {
      throw new IllegalArgumentException("Shape must disappear after it has appeared.");
    }

    disappearTime = endTime;
  }



  @Override
  public void reset() {
    vertical = startingVertical;
    horizontal = startingHorizontal;
    location = new Point2D(startingLocation.getX(), startingLocation.getY());
    color = new Color(startingColor.getRed(), startingColor.getGreen(), startingColor.getGreen());
  }
}
