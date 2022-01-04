package cs5004.animator.model;

import java.awt.Color;

/**
 * This interface provides methods needed to interact with a Shape object.  These methods allow
 * for getting and setting the attributes of a shape.
 */
public interface Shape {
  /**
   * Returns the name of the shape.  The name should be a unique identifier.
   * @return the name of the shape.
   */
  String getName();

  /**
   * Sets the location of the shape in 2D space.
   * @param p the location of the shape in 2D space.
   */
  void setLocation(Point2D p);

  /**
   * Returns the location of the shape in 2D space.
   * @return the location of the shape in 2D space.
   */
  Point2D getLocation();

  /**
   * Sets the visibility of the shape.
   * @param visibility the visibility of the shape.  true if the shape is visible, false otherwise.
   */
  void setVisibility(boolean visibility);

  /**
   * Returns  the visibility of the shape.
   * @return  the visibility of the shape.  true if the shape is visible, false otherwise.
   */
  boolean getVisibility();

  /**
   * Sets the color of the shape.
   * @param color the color of the shape.
   */
  void setColor(Color color);

  /**
   * Returns the color of the shape.
   * @return the color of the shape.
   */
  Color getColor();

  /**
   * Sets the vertical dimension of the object.
   * @param vertical the vertical dimension of the object.
   * @throws IllegalArgumentException if vertical dimension is negative
   */
  void setVertical(double vertical) throws IllegalArgumentException;

  /**
   * Returns the vertical dimension of the object.
   * @return the vertical dimension of the object.
   */
  double getVertical();

  /**
   * Sets the horizontal dimension of the object.
   * @param horizontal the horizontal dimension of the object.
   * @throws IllegalArgumentException if horizontal dimension is negative
   */
  void setHorizontal(double horizontal) throws IllegalArgumentException;

  /**
   * Returns the horizontal dimension of the object.
   * @return the horizontal dimension of the object.
   */
  double getHorizontal();

  /**
   * Returns the type of shape represented by the object.  e.g. Rectangle, Oval
   * @return the type of shape represented by the object.
   */
  ShapeType getType();

  /**
   * Returns the time at which the Shape should become visible.
   *
   * @return the time at which the Shape should become visible.
   */
  int getAppear();

  /**
   * Returns the time at which the Shape should disappear.
   *
   * @return the time at which the Shape should disappear.
   */
  int getDisappear();

  /**
   * Sets the time at which the Shape should become visible.
   *
   * @param startTime the time at which the Shape should become visible.
   */
  void setAppear(int startTime);

  /**
   * Sets the time at which the Shape should disappear.
   *
   * @param endTime the time at which the Shape should disappear.
   */
  void setDisappear(int endTime);

  /**
   * Returns a String representation of the color of the Shape.
   *
   * @return the rgb color of the Shape in the format "(red,green,blue)".
   */
  String printColor();

  /**
   * Resets the attributes of the Shape to its original configuration.
   */
  void reset();
}
