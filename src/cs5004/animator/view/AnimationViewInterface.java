package cs5004.animator.view;

import java.io.IOException;
import cs5004.animator.model.AnimationModel;

/**
 * An interface for the View of an animation.
 */
public interface AnimationViewInterface {
  /**
   * Renders the animation represented by the model.
   *
   * @param model the model that contains all the animation data.
   * @param speed the speed of the animation in ticks per second
   * @param out the output destination.  Defaults to System.out, but can be set to a data file.
   * @throws IOException if the input file does not exist
   */
  void render(AnimationModel model, int speed, Appendable out) throws IOException;

}
