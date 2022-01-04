package cs5004.animator.model;

import java.awt.Color;
import java.util.HashMap;

import cs5004.animator.util.AnimationBuilder;

/**
 * A class that populates an AnimationModel with Shapes and Animations using data read from a file.
 * The data is passed to this Builder by an AnimationReader.
 */
public class Builder implements AnimationBuilder<AnimationModel> {
  private AnimationModel model;
  private HashMap<String, Shape> shapeHashMap;

  /**
   * Initializes the AnimationBuilder with an empty model that will be populated using the methods
   * provided in this class.
   */
  public Builder() {
    model = new AnimationModelImpl();
    shapeHashMap = new HashMap<>();
  }

  @Override
  public AnimationModel build() {
    return model;
  }

  @Override
  public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
    model.setBounds(x, y, height, width);
    return this;
  }

  @Override
  public AnimationBuilder<AnimationModel> declareShape(String name, String type)
          throws IllegalArgumentException {
    Shape s;
    if (type.equalsIgnoreCase("rectangle")) {
      s = new Rectangle(name);
    } else if (type.equalsIgnoreCase("ellipse")) {
      s = new Oval(name);
    } else {
      throw new IllegalArgumentException("Invalid shape type");
    }

    shapeHashMap.put(name, s);
    model.addShape(s);

    return this;
  }

  @Override
  public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                    int h1, int r1, int g1, int b1,
                                                    int t2, int x2, int y2, int w2,
                                                    int h2, int r2, int g2, int b2) {
    Shape s = shapeHashMap.get(name);
    if (s.getAppear() == 0) {
      s.setLocation(new Point2D(x1, y1));
      s.setHorizontal(w1);
      s.setVertical(h1);
      s.setColor(new Color(r1, g1, b1));
      s.setAppear(t1);
    }

    if (h1 != h2 || w1 != w2) {
      model.addAnimation(new Resize(s, t1, t2, h1, w1, h2, w2));
    }
    if (x1 != x2 || y1 != y2) {
      model.addAnimation(new Move(s, t1, t2, new Point2D(x1, y1), new Point2D(x2, y2)));
    }
    if (r1 != r2 || g1 != g2 || b1 != b2) {
      model.addAnimation(new ColorChange(s, t1, t2, new Color(r1, g1, b1), new Color(r2, g2, b2)));
    }

    if (s.getDisappear() < t2) {
      s.setDisappear(t2);
    }
    if (model.getModelEndTime() < t2) {
      model.setModelEndTime(t2);
    }
    return this;
  }
}
