package cs5004.animator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.AnimationControllerImpl;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.Builder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AnimationViewInterface;
import cs5004.animator.view.playback.PlaybackView;
import cs5004.animator.view.svg.SVGView;
import cs5004.animator.view.swing.SwingView;
import cs5004.animator.view.textual.TextualView;


/**
 * This class contains a main method that acts as the entry point for the animation program.
 * The program takes inputs as command-line arguments.
 */
public final class EasyAnimator {
  /**
   * Acts as an entry point for the program. Takes in command line arguments as input to specify
   * the actions.
   * @param args the command line arguments
   * @throws IOException if the input file does not exist.
   */
  public static void main(String[] args) throws IOException {
    String file = "";
    Appendable out = System.out;
    int speed = 1;
    String view = "";

    for (int i = 0; i < args.length / 2; i++) {
      String flag = args[i * 2];
      switch (flag) {
        case "-in":
          file = args[i * 2 + 1];
          break;
        case "-out":
          out = new FileWriter(new File(args[i * 2 + 1]));
          break;
        case "-view":
          view = args[i * 2 + 1];
          break;
        case "-speed":
          speed = Integer.parseInt(args[i * 2 + 1]);
          break;
        default:
          // no action is intended for default case
          break;
      }
    }

    // check that view and in are properly specified

    AnimationModel model = AnimationReader.parseFile(new FileReader(new File(file)), new Builder());
    AnimationViewInterface v = viewFactory(view);

    // set up the controller here
    AnimationController controller = new AnimationControllerImpl(model, v);
    controller.run(speed, out);

  }

  static AnimationViewInterface viewFactory(String view) {
    switch (view) {
      case "text":
        return new TextualView();
      case "svg":
        return new SVGView();
      case "visual":
        return new SwingView();  // rename and implement interface
      case "playback":
        return new PlaybackView();
      default:
        // no action is intended for default case
        break;
    }
    return null;
  }
}
