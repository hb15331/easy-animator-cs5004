package cs5004.animator.view.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

/**
 * This class represents a panel object where the 2D-shapes can be drawn. The panel erases the
 * previous contents and repaints itself at regular intervals.
 */
public class ShapePanel extends JPanel {

  private List<Shape> shapes; // track the status of model's shapes
  private int currentFrame; // measured in ticks instead of milliseconds

  /**
   * Construct a ShapePanel object. It sets the shapes and time to initial values before
   * the animation starts.
   */
  public ShapePanel() {
    super();
    this.shapes = new ArrayList<>();
    this.currentFrame = 0;
  }

  /**
   * Update the list of shapes to be drawn.
   * @param shapes the list of modified shapes from the model.
   */
  public void updatePanel(List<Shape> shapes) {
    // update the shapes to be drawn based on the data from model
    this.shapes = shapes;
  }

  /**
   * Increment the time as the animation progresses.
   * @param ticks the number of clock ticks since the animation starts
   */
  public void updateFrame(int ticks) {
    this.currentFrame = ticks;
  }

  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (Shape s : shapes) {
      double x = s.getLocation().getX();
      double y = s.getLocation().getY();

      g2d.setColor(s.getColor());

      // check whether shape should be drawn at this clock tick
      // do this check for every shape because each may appear at different time
      if (currentFrame >= s.getAppear() && currentFrame < s.getDisappear()) {

        if (s instanceof Rectangle) {
          Rectangle2D rect2D = new Rectangle2D.Double(x, y, s.getHorizontal(), s.getVertical());
          g2d.fill(rect2D); // fill the shape with color
          g2d.draw(rect2D);
        } else {
          Ellipse2D oval2D = new Ellipse2D.Double(x, y, s.getHorizontal(), s.getVertical());
          g2d.fill(oval2D);
          g2d.draw(oval2D);
        }
      }
    }
  }


}
