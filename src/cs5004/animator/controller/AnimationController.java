package cs5004.animator.controller;

import java.io.IOException;

/**
 * An interface for a controller class that runs an animation.  This controller works with a model
 * of type AnimationModel, and a view of type AnimationViewInterface.  The controller also accepts
 * an integer for the speed of the animation and an Appendable for the output of the animation.
 */
public interface AnimationController {
  /**
   * Runs the animation.
   *
   * @param speed the speed of the animation.
   * @param out   the output destination for the animation.
   * @throws IOException if the output is not writable.
   */
  void run(int speed, Appendable out) throws IOException;
}
