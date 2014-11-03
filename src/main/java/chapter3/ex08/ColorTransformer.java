package chapter3.ex08;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransformer {
  Color apply(int x, int y, Color colorAtXY);
  
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
