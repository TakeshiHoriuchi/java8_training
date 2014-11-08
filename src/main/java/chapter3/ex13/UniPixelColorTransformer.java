package chapter3.ex13;

import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface UniPixelColorTransformer {
  Color apply(int x, int y, Color colorAtXY);
  
  default BaseColorTransformer toBaseColorTransformer() {
    return (x, y, reader) -> apply(x, y, reader.getColor(x, y));
  }
  
  static UniPixelColorTransformer compose(UniPixelColorTransformer ct1, UniPixelColorTransformer ct2) {
    return (x, y, colorAtXY) -> ct1.apply(x, y, ct2.apply(x, y, colorAtXY));
  }
  
  static UniPixelColorTransformer from(UnaryOperator<Color> func) {
    return (x, y, colrAtXY) -> func.apply(colrAtXY);
  }
  
  static UniPixelColorTransformer enclose(Image in, int length, Color color) {
    int width = (int) in.getWidth();
    int height = (int) in.getHeight();
    
    return (x, y, c) -> {
      if (x <= length || width - x <= length || y <= length || height - y <= length)
        return color;
      else 
        return c;
    };
  }
}
