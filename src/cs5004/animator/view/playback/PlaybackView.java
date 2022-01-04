package cs5004.animator.view.playback;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;


import javax.swing.Timer;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.InteractiveViewInterface;

/**
 * This class allows users to view the animation in Java Swing. It implements AnimationViewInterface
 * to initialize the view settings based on the data from model. Also, it implements ActionListener
 * interface to automate the paintings at regular intervals.
 */
public class PlaybackView implements InteractiveViewInterface, ActionListener {

  private AnimationModel model;
  private EditorPanel canvas;
  private int delay; // milliseconds between two ticks

  private boolean isPlaying;
  private Timer timer;
  private boolean loopback;

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
    isPlaying = false;
    loopback = false;

    // get canvas attrs from the model
    int canvasX = model.getLeft();
    int canvasY = model.getTop();
    int canvasWidth = model.getWidth();
    int canvasHeight = model.getHeight();
    // initialize the canvas
    this.canvas = new EditorPanel(canvasX, canvasY, canvasWidth, canvasHeight);

    // scale the time in milliseconds
    this.delay = 1000 / speed;
    canvas.setSpeedText(speed);
    // timer triggers the actionPerformed after regular delay
    timer = new Timer(delay, this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (model.getCurrentFrame() < model.getModelEndTime()) {
      model.nextFrame();
      // send the updated shapes to canvas
      canvas.updatePanel(model.getShapes());
      canvas.updateFrame(model.getCurrentFrame());
      // ask the panel to repaint itself
      canvas.refresh();
    } else {
      if (!loopback) {
        toggleTimer();
      }
      model.reset();
    }

  }

  @Override
  public void setButtonListener(ActionListener actionEvent) {
    canvas.setButtonListener(actionEvent);
  }

  @Override
  public void setItemListener(ItemListener listener) {
    canvas.setItemListener(listener);
  }

  @Override
  public void toggleTimer() {
    if (isPlaying) {
      timer.stop();
      canvas.toggleButtonText("Play");
    } else {
      timer.start();
      canvas.toggleButtonText("Pause");
    }
    isPlaying = !isPlaying;
  }

  @Override
  public void setSpeed(int speed) {
    this.delay = 1000 / speed;
    timer.setDelay(delay);
    canvas.setSpeedText(speed);
  }

  @Override
  public void toggleLoopback() {
    loopback = !loopback;
  }

}
