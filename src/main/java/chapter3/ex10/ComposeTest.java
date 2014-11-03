/*
型変数Vのインスタンスが存在しないので、Function<V,Color>はUnaryOperator<Color>に適合しませんとなりエラー
*/


package chapter3.ex10;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class ComposeTest {
  void test() {
    UnaryOperator<Color> op = Color::brighter;
    Image im = null;
    
//    im = transform(im, op.compose(Color::grayscale));
    
    Function<Color, Color> op1 = op.compose(Color::grayscale); // これは可能
  }
  
  Image transform(Image in, UnaryOperator<Color> f) {
    return in;
  }
}
