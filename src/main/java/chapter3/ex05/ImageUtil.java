package chapter3.ex05;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class ImageUtil {
  public static Image transform(Image in, ColorTransformer ct) {
    int width = (int) in.getWidth();
    int height = (int) in.getHeight();
    WritableImage out = new WritableImage(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        out.getPixelWriter().setColor(
                x,
                y,
                ct.apply(
                        x,
                        y,
                        in.getPixelReader().getColor(x, y)
                )
        );
      }
    }
    
    return out;
  }
}