package cs5004.animator.view.playback;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs5004.animator.model.Shape;
import cs5004.animator.view.swing.ShapePanel;

/**
 * This class represents the top-level frame in which all the Swing components are organized. It
 * contains a JPanel component where 2D-graphics are drawn. Also, it contains the components
 * (such as buttons and checkbox) that allows for user interaction with the program.
 */
public class EditorPanel extends JFrame {

  private ShapePanel myPanel;
  private JButton restart;
  private JButton playPause;
  private JButton faster;
  private JLabel speedText;
  private JButton slower;
  private JCheckBox loopback;

  /**
   * Construct a EditorPanel object and initialize its position, layout and size.
   *
   * @param canvasX x-coordinate of canvas's top-left corner
   * @param canvasY y-coordinate of canvas's top-left corner
   * @param canvasWidth width of the canvas
   * @param canvasHeight height of the canvas
   */
  public EditorPanel(int canvasX, int canvasY, int canvasWidth, int canvasHeight) {
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

    // button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);
    // add buttons

    restart = new JButton("Restart");
    restart.setActionCommand("restart");
    buttonPanel.add(restart);

    playPause = new JButton("Play");
    playPause.setActionCommand("playPause");
    buttonPanel.add(playPause);

    slower = new JButton("-");
    slower.setActionCommand("slower");
    buttonPanel.add(slower);

    // add some text here about the current speed
    speedText = new JLabel("current speed: ");
    buttonPanel.add(speedText);

    faster = new JButton("+");
    faster.setActionCommand("faster");
    buttonPanel.add(faster);

    buttonPanel.add(new JLabel("loopback: "));

    loopback = new JCheckBox();
    buttonPanel.add(loopback);

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
  public void updateFrame(int ticks) {
    myPanel.updateFrame(ticks);
  }

  /**
   * Ask the panel to repaint its graphics.
   */
  public void refresh() {
    // repaint the current state of this frame, including any component it contains
    this.repaint();
  }

  /**
   * Set the given object to giving response to the event triggered by a Swing component.
   * @param actionEvent the given object that will respond to an event triggered by component
   */
  public void setButtonListener(ActionListener actionEvent) {
    playPause.addActionListener(actionEvent);
    restart.addActionListener(actionEvent);
    faster.addActionListener(actionEvent);
    slower.addActionListener(actionEvent);
    loopback.addActionListener(actionEvent);
  }

  /**
   * Set the item listener for the loopback checkbox.
   * @param listener the given object the serves as the action listener
   */
  public void setItemListener(ItemListener listener) {
    loopback.addItemListener(listener);
  }

  /**
   * Set the text to be shown on the button when switching between play and pause.
   * @param text the given text to be set on the button
   */
  public void toggleButtonText(String text) {
    playPause.setText(text);
  }

  /**
   * Set the value of current play speed to be displayed on the screen.
   * @param speed the given speed that is shown on the screen
   */
  public void setSpeedText(int speed) {
    speedText.setText("current speed: " + speed + " fps");
  }


}
