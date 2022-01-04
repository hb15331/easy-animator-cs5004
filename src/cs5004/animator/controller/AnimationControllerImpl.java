package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.AnimationViewInterface;
import cs5004.animator.view.InteractiveViewInterface;

/**
 * A concrete implementation of the controller for an animation.
 */
public class AnimationControllerImpl implements AnimationController, ActionListener, ItemListener {
  private AnimationModel model;
  private AnimationViewInterface view;
  private InteractiveViewInterface interactiveView;
  private int speed;
  //private Appendable out;

  /**
   * Initializes the controller with its model and view.
   *
   * @param model the model for the animation.
   * @param view the view for the animation.
   */
  public AnimationControllerImpl(AnimationModel model, AnimationViewInterface view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void run(int speed, Appendable out) throws IOException {
    this.speed = speed;
    //this.out = out;
    view.render(model, speed, out);
    if (view instanceof InteractiveViewInterface) {
      interactiveView = (InteractiveViewInterface) view;
      this.interactiveView.setButtonListener(this);
      this.interactiveView.setItemListener(this);
    }
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {

    switch (actionEvent.getActionCommand()) {
      case "playPause":
        interactiveView.toggleTimer();
        break;
      case "restart":
        model.reset();
        break;
      case "faster":
        speed++;
        interactiveView.setSpeed(speed);
        break;
      case "slower":
        if (speed > 1) {
          speed--;
          interactiveView.setSpeed(speed);
        }
        break;
      default:
        // no action is intended for default case
        break;
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    interactiveView.toggleLoopback();
  }
}
