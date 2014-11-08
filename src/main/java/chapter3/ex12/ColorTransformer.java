package chapter3.ex12;

import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransformer {
  Color apply(int x, int y, Color colorAtXY);
  
  static ColorTransformer compose(ColorTransformer ct1, ColorTransformer ct2) {
    return (x, y, colorAtXY) -> ct1.apply(x, y, ct2.apply(x, y, colorAtXY));
  }
  
  static ColorTransformer from(UnaryOperator<Color> func) {
    return (x, y, colrAtXY) -> func.apply(colrAtXY);
  }
  
  static ColorTransformer enclose(Image in, int length, Color color) {
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
