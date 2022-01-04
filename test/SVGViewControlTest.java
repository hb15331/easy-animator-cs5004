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
import cs5004.animator.view.svg.SVGView;

import static org.junit.Assert.assertEquals;


/**
 * This class contains the JUnit tests for the controller class. It is testing whether the
 * controller can work with the SVG view and yields the correct textual information.
 */
public class SVGViewControlTest {

  private AnimationController ac;

  @Test
  public void testControlSVGSmallDemo() throws IOException {

    AnimationModel model = AnimationReader
            .parseFile(new FileReader(new File("smalldemo.txt")), new Builder());
    StringBuilder out = new StringBuilder();
    AnimationViewInterface v = new SVGView();
    ac = new AnimationControllerImpl(model, v);
    ac.run(20, out);

    String[] lines = out.toString().split("\n");
    // run tests on out which contains the text of the SVG file

    // check for number of lines
    assertEquals(19, lines.length);

    // check number of animations
    assertEquals(9, out.toString().split("animate").length - 1);

    // check number of rectangles
    assertEquals(1, out.toString().split("<rect").length - 1);

    // check number of ellipses
    assertEquals(1, out.toString().split("<ellipse").length - 1);

    // check header
    assertEquals("<svg viewBox=\"200 70 360 360\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">", lines[0]);

    // ends with svg tag
    assertEquals("</svg>", lines[lines.length - 1]);

    int numMovesRectX = 0;
    int numMovesRectY = 0;
    int numMovesOvalX = 0;
    int numMovesOvalY = 0;
    int numResizeRectX = 0;
    int numResizeRectY = 0;
    int numResizeOvalX = 0;
    int numResizeOvalY = 0;
    int numColorChange = 0;
    int appear = 0;
    for (String line : lines) {
      if (line.contains("attributeName=\"x\" from=")) {
        numMovesRectX++;
      }
      if (line.contains("attributeName=\"y\" from=")) {
        numMovesRectY++;
      }
      if (line.contains("attributeName=\"cx\" from=")) {
        numMovesOvalX++;
      }
      if (line.contains("attributeName=\"cy\" from=")) {
        numMovesOvalY++;
      }
      if (line.contains("attributeName=\"width\" from=")) {
        numResizeRectX++;
      }
      if (line.contains("attributeName=\"height\" from=")) {
        numResizeRectY++;
      }
      if (line.contains("attributeName=\"rx\" from=")) {
        numResizeOvalX++;
      }
      if (line.contains("attributeName=\"ry\" from=")) {
        numResizeOvalY++;
      }
      if (line.contains("from=\"rgb(")) {
        numColorChange++;
      }
      if (line.contains("<set")) {
        appear++;
      }
    }
    assertEquals(2, numMovesRectX);
    assertEquals(2, numMovesRectY);
    assertEquals(0, numMovesOvalX);
    assertEquals(2, numMovesOvalY);
    assertEquals(1, numResizeRectX);
    assertEquals(0, numResizeRectY);
    assertEquals(0, numResizeOvalX);
    assertEquals(0, numResizeOvalY);
    assertEquals(2, numColorChange);
    assertEquals(1, appear);

  }


  @Test
  public void testControlSVGToh() throws IOException {

    AnimationModel model = AnimationReader
            .parseFile(new FileReader(new File("toh-3.txt")), new Builder());
    StringBuilder out = new StringBuilder();
    AnimationViewInterface v = new SVGView();
    ac = new AnimationControllerImpl(model, v);
    ac.run(20, out);

    // check number of rectangles
    assertEquals(3, out.toString().split("<rect").length - 1);

    // check number of ellipses
    assertEquals(0, out.toString().split("<ellipse").length - 1);

    String[] lines = out.toString().split("\n");
    int numMovesRectX = 0;
    int numMovesRectY = 0;
    int numMovesOvalX = 0;
    int numMovesOvalY = 0;
    int numResizeRectX = 0;
    int numResizeRectY = 0;
    int numResizeOvalX = 0;
    int numResizeOvalY = 0;
    int numColorChange = 0;
    int appear = 0;
    for (String line : lines) {
      if (line.contains("attributeName=\"x\" from=")) {
        numMovesRectX++;
      }
      if (line.contains("attributeName=\"y\" from=")) {
        numMovesRectY++;
      }
      if (line.contains("attributeName=\"cx\" from=")) {
        numMovesOvalX++;
      }
      if (line.contains("attributeName=\"cy\" from=")) {
        numMovesOvalY++;
      }
      if (line.contains("attributeName=\"width\" from=")) {
        numResizeRectX++;
      }
      if (line.contains("attributeName=\"height\" from=")) {
        numResizeRectY++;
      }
      if (line.contains("attributeName=\"rx\" from=")) {
        numResizeOvalX++;
      }
      if (line.contains("attributeName=\"ry\" from=")) {
        numResizeOvalY++;
      }
      if (line.contains("from=\"rgb(")) {
        numColorChange++;
      }
      if (line.contains("<set")) {
        appear++;
      }
    }
    assertEquals(7, numMovesRectX);
    assertEquals(14, numMovesRectY);
    assertEquals(0, numMovesOvalX);
    assertEquals(0, numMovesOvalY);
    assertEquals(0, numResizeRectX);
    assertEquals(0, numResizeRectY);
    assertEquals(0, numResizeOvalX);
    assertEquals(0, numResizeOvalY);
    assertEquals(3, numColorChange);
    assertEquals(0, appear);

  }

  @Test
  public void testControlSVGBigBang() throws IOException {

    AnimationModel model = AnimationReader
            .parseFile(new FileReader(new File("big-bang-big-crunch.txt")), new Builder());
    StringBuilder out = new StringBuilder();
    AnimationViewInterface v = new SVGView();
    ac = new AnimationControllerImpl(model, v);
    ac.run(20, out);

    // check number of rectangles
    assertEquals(0, out.toString().split("<rect").length - 1);

    // check number of ellipses
    assertEquals(5000, out.toString().split("<ellipse").length - 1);

    String[] lines = out.toString().split("\n");
    int numMovesRectX = 0;
    int numMovesRectY = 0;
    int numResizeRectX = 0;
    int numResizeRectY = 0;
    int numResizeOvalX = 0;
    int numResizeOvalY = 0;
    int numColorChange = 0;
    int appear = 0;
    for (String line : lines) {
      if (line.contains("attributeName=\"x\" from=")) {
        numMovesRectX++;
      }
      if (line.contains("attributeName=\"y\" from=")) {
        numMovesRectY++;
      }
      if (line.contains("attributeName=\"width\" from=")) {
        numResizeRectX++;
      }
      if (line.contains("attributeName=\"height\" from=")) {
        numResizeRectY++;
      }
      if (line.contains("attributeName=\"rx\" from=")) {
        numResizeOvalX++;
      }
      if (line.contains("attributeName=\"ry\" from=")) {
        numResizeOvalY++;
      }
      if (line.contains("from=\"rgb(")) {
        numColorChange++;
      }
      if (line.contains("<set")) {
        appear++;
      }
    }
    assertEquals(0, numMovesRectX);
    assertEquals(0, numMovesRectY);
    // omitted oval move counts because it isn't reasonable to do manually
    assertEquals(0, numResizeRectX);
    assertEquals(0, numResizeRectY);
    assertEquals(0, numResizeOvalX);
    assertEquals(0, numResizeOvalY);
    assertEquals(0, numColorChange);
    assertEquals(0, appear);

  }

  @Test (expected = IllegalArgumentException.class)
  public void zeroSpeedControllerTest() throws IOException {

    AnimationModel model = AnimationReader
            .parseFile(new FileReader(new File("smalldemo.txt")), new Builder());
    StringBuilder out = new StringBuilder();
    AnimationViewInterface v = new SVGView();
    ac = new AnimationControllerImpl(model, v);
    ac.run(0, out);

  }

  @Test (expected = IllegalArgumentException.class)
  public void negativeSpeedControllerTest() throws IOException {

    AnimationModel model = AnimationReader
            .parseFile(new FileReader(new File("smalldemo.txt")), new Builder());
    StringBuilder out = new StringBuilder();
    AnimationViewInterface v = new SVGView();
    ac = new AnimationControllerImpl(model, v);
    ac.run(-20, out);

  }

}
