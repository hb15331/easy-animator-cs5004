package cs5004.animator.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.AnimationViewInterface;

/**
 * This class allows users to view the animation in Java Swing. It implements AnimationViewInterface
 * to initialize the view settings based on the data from model. Also, it implements ActionListener
 * interface to automate the paintings at regular intervals.
 */
public class SwingView implements AnimationViewInterface, ActionListener {

  private AnimationModel model;
  private SwingPanel canvas;
  private int currentTime;
  private int delay; // milliseconds between two ticks


  /**
   * Creates the visual representation of the model.
   *
   * @param model the model that contains all the animation data.
   * @param speed the speed of the animation in ticks per second
   * @param out   the output destination.  Defaults to System.out, but can be set to a data file.
   * @throws IllegalArgumentException if the speed is not positive
   */
  @Override
  public void render(AnimationModel model, int speed, Appendable out)
          throws IllegalArgumentException {
    if (speed <= 0) {
      throw new IllegalArgumentException("Invalid speed value");
    }

    this.model = model;

    // get canvas attrs from the model
    int canvasX = model.getLeft();
    int canvasY = model.getTop();
    int canvasWidth = model.getWidth();
    int canvasHeight = model.getHeight();
    // initialize the canvas
    this.canvas = new SwingPanel(canvasX, canvasY, canvasWidth, canvasHeight);

    // scale the time in milliseconds
    this.delay = 1000 / speed;
    this.currentTime = -delay;
    // timer triggers the actionPerformed after regular delay
    Timer timer = new Timer(delay, this);
    // animation starts from here
    timer.start();

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    model.nextFrame();
    // get the updated list of shapes

    // send the updated shapes to canvas
    canvas.updatePanel(model.getShapes());

    // current time advances in milliseconds
    currentTime += delay;
    // convert milliseconds to ticks for comparison in panel
    canvas.updateTime(currentTime / delay);

    // ask the panel to repaint itself
    canvas.refresh();

  }
}
