import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.AnimationControllerImpl;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.Builder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AnimationViewInterface;
import cs5004.animator.view.textual.TextualView;

import static org.junit.Assert.assertEquals;


/**
 * This class contains the JUnit tests for the controller class. It is testing whether the
 * controller can work with the Textual view and yields the correct textual information.
 */
public class TextualViewControlTest {

  private AnimationController ac;

  @Test
  public void testControlTextualSmallDemo() throws IOException {

    // test the textual view
    AnimationModel model = AnimationReader
            .parseFile(new FileReader(new File("smalldemo.txt")), new Builder());
    StringBuilder out = new StringBuilder();
    AnimationViewInterface textView = new TextualView();
    // set up the controller
    ac = new AnimationControllerImpl(model, textView);
    ac.run(20, out);

    String[] lines = out.toString().split("\n");

    // test the number of lines in the output text
    assertEquals(13, lines.length);
    // test the first line
    assertEquals("Create rectangle R with corner at (200.0,200.0) width 50.0, "
            + "height 100.0, rgb color (255, 0, 0)", lines[0]);
    // test the last line
    assertEquals("C changes color from (0, 170, 85) to (0, 255, 0) "
            + "from t=70 to t=80", lines[12]);

    // test the number of shapes and the number of animations
    int numMoves = 0;
    int numResize = 0;
    int numColorChange = 0;
    int numRects = 0;
    int numOvals = 0;
    for (int i = 0; i < lines.length; ++i) {
      if (lines[i].contains("moves")) {
        numMoves++;
      }
      if (lines[i].contains("scales")) {
        numResize++;
      }
      if (lines[i].contains("changes color")) {
        numColorChange++;
      }
      if (lines[i].contains("rectangle")) {
        numRects++;
      }
      if (lines[i].contains("oval")) {
        numOvals++;
      }
    }
    assertEquals(4, numMoves);
    assertEquals(1, numResize);
    assertEquals(2, numColorChange);
    assertEquals(1, numRects);
    assertEquals(1, numOvals);

  }


  @Test
  public void testControlTextualToh() throws IOException {

    // test the textual view
    AnimationModel model = AnimationReader
            .parseFile(new FileReader(new File("toh-3.txt")), new Builder());
    StringBuilder out = new StringBuilder();
    AnimationViewInterface textView = new TextualView();
    // set up the controller
    ac = new AnimationControllerImpl(model, textView);
    ac.run(20, out);

    String[] lines = out.toString().split("\n");

    // test the number of lines in the output text
    assertEquals(32, lines.length);
    // test the first line
    assertEquals("Create rectangle disk1 with corner at (190.0,180.0) "
            + "width 20.0, height 30.0, rgb color (0, 49, 90)", lines[0]);
    // test the last line
    assertEquals("disk1 changes color from (0, 49, 90) "
            + "to (0, 255, 0) from t=249 to t=257", lines[31]);

    // test the number of shapes and the number of animations
    int numMoves = 0;
    int numResize = 0;
    int numColorChanges = 0;
    int numRects = 0;
    int numOvals = 0;
    for (int i = 0; i < lines.length; i++) {
      if (lines[i].contains("changes color")) {
        numColorChanges++;
      }
      if (lines[i].contains("rectangle")) {
        numRects++;
      }
      if (lines[i].contains("oval")) {
        numOvals++;
      }
      if (lines[i].contains("moves")) {
        numMoves++;
      }
      if (lines[i].contains("scales")) {
        numResize++;
      }
    }
    assertEquals(21, numMoves);
    assertEquals(0, numResize);
    assertEquals(3, numColorChanges);
    assertEquals(3, numRects);
    assertEquals(0, numOvals);

  }

  @Test
  public void testControlTextualBigBang() throws IOException {

    // test the textual view
    AnimationModel model = AnimationReader
            .parseFile(new FileReader(new File("big-bang-big-crunch.txt")), new Builder());
    StringBuilder out = new StringBuilder();
    AnimationViewInterface textView = new TextualView();
    // set up the controller
    ac = new AnimationControllerImpl(model, textView);
    ac.run(20, out);

    String[] lines = out.toString().split("\n");

    // test the first line
    assertEquals("Create oval P1 with center at (400.0,400.0), X radius 4.0 "
            + "Y radius 4.0, rgb color (240, 122, 106)", lines[0]);

    // test the number of shapes and the number of animations
    int numOvals = 0;
    int numRects = 0;
    int numResize = 0;
    int numColorChanges = 0;
    for (int i = 0; i < lines.length; i++) {
      if (lines[i].contains("oval")) {
        numOvals++;
      }
      if (lines[i].contains("rectangle")) {
        numRects++;
      }
      if (lines[i].contains("scales")) {
        numResize++;
      }
      if (lines[i].contains("changes color")) {
        numColorChanges++;
      }
    }
    // omitted oval move count because not reasonable to do this manually
    assertEquals(0, numRects);
    assertEquals(5000, numOvals);
    assertEquals(0, numResize);
    assertEquals(0, numColorChanges);

  }

  @Test
  public void testControlTextualBuildings() throws IOException {

    // test the textual view
    AnimationModel model = AnimationReader
            .parseFile(new FileReader(new File("buildings.txt")), new Builder());
    StringBuilder out = new StringBuilder();
    AnimationViewInterface textView = new TextualView();
    // set up the controller
    ac = new AnimationControllerImpl(model, textView);
    ac.run(20, out);

    String[] lines = out.toString().split("\n");

    // test the first line
    assertEquals("Create rectangle background with corner at (0.0,0.0) "
            + "width 800.0, height 800.0, rgb color (33, 94, 248)", lines[0]);

    // test the number of rectangles and ovals
    int numRects = 0;
    int numOvals = 0;
    int numOvalMoves = 0;
    int numResize = 0;
    int numOvalColorChange = 0;
    for (int i = 0; i < lines.length; i++) {
      if (lines[i].contains("rectangle")) {
        numRects++;
      }
      if (lines[i].contains("oval")) {
        numOvals++;
      }
      if (lines[i].contains("eclipse moves")) {
        numOvalMoves++;
      }
      if (lines[i].contains("scales")) {
        numResize++;
      }
      if (lines[i].contains("eclipse changes color")) {
        numOvalColorChange++;
      }
    }
    assertEquals(67, numRects);
    assertEquals(42, numOvals);
    assertEquals(1, numOvalMoves);
    assertEquals(0, numResize);
    assertEquals(1, numOvalColorChange);

  }

  @Test(expected = IllegalArgumentException.class)
  public void zeroSpeedControllerTest() throws IOException {

    AnimationModel model = AnimationReader
            .parseFile(new FileReader(new File("smalldemo.txt")), new Builder());
    StringBuilder out = new StringBuilder();
    AnimationViewInterface textView = new TextualView();
    ac = new AnimationControllerImpl(model, textView);
    ac.run(0, out);

  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeControllerTest() throws IOException {

    AnimationModel model = AnimationReader
            .parseFile(new FileReader(new File("smalldemo.txt")), new Builder());
    StringBuilder out = new StringBuilder();
    AnimationViewInterface textView = new TextualView();
    ac = new AnimationControllerImpl(model, textView);
    ac.run(-20, out);

  }

}
