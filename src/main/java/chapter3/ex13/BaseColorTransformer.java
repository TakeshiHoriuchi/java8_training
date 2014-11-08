package chapter3.ex13;

import java.util.function.UnaryOperator;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public interface BaseColorTransformer {
  Color apply(int x, int y, PixelReader reader);
  
  static BaseColorTransformer from(UnaryOperator<Color> f) {
    return (x, y, reader) -> f.apply(reader.getColor(x, y));
  } 
}
