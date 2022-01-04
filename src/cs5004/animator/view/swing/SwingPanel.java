package cs5004.animator.view.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs5004.animator.model.Shape;

/**
 * This class represents the top-level frame (or canvas) in which all the events should occur. It
 * contains a JPanel component where 2D-graphics are drawn.
 */
public class SwingPanel extends JFrame {

  private ShapePanel myPanel;

  /**
   * Construct a SwingPanel object and initialize its position, layout and size.
   * @param canvasX x-coordinate of canvas's top-left corner
   * @param canvasY y-coordinate of canvas's top-left corner
   * @param canvasWidth width of the canvas
   * @param canvasHeight height of the canvas
   */
  public SwingPanel(int canvasX, int canvasY, int canvasWidth, int canvasHeight) {
    super();
    this.setTitle("MyView");
    this.setSize(canvasWidth, canvasHeight); // size of frame
    this.setLocation(canvasX, canvasY); // location of frame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // use a border layout with drawing panel in center
    this.setLayout(new BorderLayout());
    // add JPanel to this frame's components
    myPanel = new ShapePanel();
    // make panel bigger so that it becomes scrollable
    myPanel.setPreferredSize(new Dimension(canvasWidth + 1000, canvasHeight + 1000));
    this.add(myPanel, BorderLayout.CENTER);

    // add a scrollPane to the frame
    JScrollPane scrollPane = new JScrollPane(myPanel);
    scrollPane.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    // set the initial position of user's view
    scrollPane.getViewport().setViewPosition(new Point(100, 100));
    this.add(scrollPane, BorderLayout.CENTER);

    // cause the frame to be sized to fit the preferred size and layouts of its subcomponents
    this.pack();
    // makes the frame visible once it is created
    this.setVisible(true);

  }

  /**
   * Transfer the list of modified shapes from the model to the panel.
   * @param shapes the list of modified shapes from model
   */
  public void updatePanel(List<Shape> shapes) {
    myPanel.updatePanel(shapes);
  }

  /**
   * Update the time of the panel as the animation progresses.
   * @param ticks the number of clock ticks since animation starts
   */
  public void updateTime(int ticks) {
    myPanel.updateFrame(ticks);
  }

  /**
   * Ask the panel to repaint its graphics.
   */
  public void refresh() {
    // repaint the current state of this frame, including any component it contains
    this.repaint();
  }

}
