package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * An interface for animation views that have interactive features.  This interface supports
 * interactions that allow for pausing, playing, changing speed, and auto-replaying.
 */
public interface InteractiveViewInterface extends AnimationViewInterface {
  /**
   * Passes the controller to the view to set the controller as the listener.
   *
   * @param actionEvent the listener for button events.
   */
  void setButtonListener(ActionListener actionEvent);

  /**
   * Passes the controller to the view to set the controller as the listener.
   *
   * @param listener the listener for checkbox events.
   */
  void setItemListener(ItemListener listener);

  /**
   * Each call will change the toggle the animation between playing and paused.  If the animation is
   * playing, it will pause the animation.  If it is paused, this method will start it.
   */
  void toggleTimer();

  /**
   * Changes the speed of the animation.
   *
   * @param speed the speed of the animation (in frames per second).
   */
  void setSpeed(int speed);

  /**
   * Toggles the loopback status of the animation.  This method will toggle a boolean that
   * determines whether or not the animation will automatically replay upon completion.
   */
  void toggleLoopback();


}
