package cs5004.animator.model;

import java.awt.Color;

/**
 * A type of cs5004.animator.model.Animation that gradually changes the color of a given shape.
 * Given a target color, this class calculates a linear progression of RGB values that gradually
 * alters the Shapes color from its original color to the target color.
 */
public class ColorChange extends AbstractAnimation {
  private Color startingColor;
  private Color endColor;

  private double speedRed;
  private double speedBlue;
  private double speedGreen;

  /**
   * Initializes a ColorChange animation with its target shape, target color, and time interval.
   *
   * @param s the cs5004.animator.model.Shape which will be changing color.
   * @param startTime the time at which the animation will begin.
   * @param endTime the time at which the animation will end.
   * @param start the color of the shape at the beginning of the animation.
   * @param end the final color of the shape at the end of the animation.
   * @throws IllegalArgumentException if start or end times are negative, or if start is after end.
   */
  public ColorChange(Shape s, int startTime, int endTime, Color start, Color end)
          throws IllegalArgumentException {
    super(s, startTime, endTime);
    startingColor = start;
    endColor = end;
    speedRed = 1.0 * (endColor.getRed() - startingColor.getRed()) / (endTime - startTime);
    speedBlue = 1.0 * (endColor.getBlue() - startingColor.getBlue()) / (endTime - startTime);
    speedGreen = 1.0 * (endColor.getGreen() - startingColor.getGreen()) / (endTime - startTime);
  }

  @Override
  public void modifyShape(int time) {
    if (time > startTime && time <= endTime) {
      int red = startingColor.getRed() + (int) ((time - startTime) * speedRed);
      int blue = startingColor.getBlue() + (int) ((time - startTime) * speedBlue);
      int green = startingColor.getGreen() + (int) ((time - startTime) * speedGreen);
      s.setColor(new Color(red, green, blue));
    }
  }

  @Override
  public String toString() {
    return "Shape " + s.getName() + " changes color from " + printColor(startingColor)
            + " to " + printColor(endColor) + " from t=" + startTime + " to t=" + endTime + "\n";
  }

  /**
   * Helper function that outputs the RGB value of a color.
   * @param c the specified color to be printed
   * @return the formatted string of color RGB
   */
  private String printColor(Color c) {
    return String.format("(%d, %d, %d)", c.getRed(), c.getGreen(), c.getBlue());
  }

  /**
   * Returns the color of the Shape at the beginning of the animation.
   *
   * @return the color of the Shape at the beginning of the animation.
   */
  public String printStartColor() {
    return printColor(startingColor);
  }

  /**
   * Returns the color of the Shape at the end of the animation.
   *
   * @return the color of the Shape at the end of the animation.
   */
  public String printEndColor() {
    return printColor(endColor);
  }

  @Override
  public AnimationType getType() {
    return AnimationType.COLOR_CHANGE;
  }

}

