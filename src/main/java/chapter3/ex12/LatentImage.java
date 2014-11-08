package chapter3.ex12;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {

  private final Image in;
  private final List<ColorTransformer> pendingOperations = new LinkedList<>();

  public LatentImage(Image in) { this.in = in; }

  public LatentImage transform(UnaryOperator<Color> f) {
    pendingOperations.add(ColorTransformer.from(f));
    return this;
  }

  public LatentImage transform(ColorTransformer ct) {
    pendingOperations.add(ct);
    return this;
  }

  public Image toImage() {
    int width = (int) in.getWidth();
    int height = (int) in.getHeight();
    WritableImage out = new WritableImage(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color c = in.getPixelReader().getColor(x, y);
        for (ColorTransformer ct : pendingOperations) c = ct.apply(x, y, c);
        out.getPixelWriter().setColor(x, y, c);
      }
    }

    return out;
  }
}
