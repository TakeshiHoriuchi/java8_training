package chapter3.ex06;

import java.util.function.BiFunction;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class ImageUtil {
  public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
    int width = (int) in.getWidth();
    int height = (int) in.getHeight();
    WritableImage out = new WritableImage(width, height);
    PixelWriter writer = out.getPixelWriter();
    PixelReader reader = in.getPixelReader();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        writer.setColor(x, y, f.apply(reader.getColor(x, y), arg));
      }
    }
    
    return out;
  }
}
