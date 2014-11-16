package chapter3.ex13;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import org.junit.Test;
import static org.junit.Assert.*;
import util.ImageTransformTestUtil;
import util.IsEvaluated;

/*
畳み込みフィルタのテストはLatentImage自体で変換した画像を目視した後、
保存してテスト期待値の画像としている

オーバーライドされたtransform メソッドのうち、
どれを用いるかコンパイラが推論できずコンパイルエラーになる場合がある。
一方で、コンパイルが成功する場合もある。
*/
public class LatentImageTest extends ImageTransformTestUtil {

  @Test
  public void testTransform_UnaryOperator_not_evaluate_function() {
    LatentImage limage = new LatentImage(in);
    IsEvaluated ise = new IsEvaluated();
    limage.transform((c) -> {
      ise.isEval = true;
      return c;
    });
    assertFalse(ise.isEval);
  }

  @Test
  public void testTransform_UniPixelColorTransformer_not_evaluate_function() {
    LatentImage limage = new LatentImage(in);
    IsEvaluated ise = new IsEvaluated();
    limage.transform((x, y, c) -> {
      ise.isEval = true;
      return c;
    });
    assertFalse(ise.isEval);
  }

  @Test
  public void testToImage_evaluate_all_saved_functions() {
    LatentImage limage = new LatentImage(in);
    limage.transform(c -> c.deriveColor(0, 1, 1.2, 1)).
            transform((x, y, c) -> x <= 10 ? Color.ALICEBLUE : c);

    PixelReader actPReader = limage.toImage().getPixelReader();
    applyAllPixels((x, y) -> {
      if (x <= 10) {
        assertEquals(Color.ALICEBLUE, actPReader.getColor(x, y));
      }
      else {
        assertEquals(
                Math.min(expPReader.getColor(x, y).getBrightness() * 1.2, 1.0),
                actPReader.getColor(x, y).getBrightness(),
                0.01
        );
      }
    });
  }

  @Test
  public void testToImage_evaluate_convolution_function() {
    LatentImage limage = new LatentImage(in);
    limage.transform(new ConvolutionTransformer(new double[][]{{0, -1, 0},
                                                               {-1, 4, -1},
                                                               {0, -1, 0}}));

    PixelReader actPReader = limage.toImage().getPixelReader();
    PixelReader expPReader = new Image(LatentImageTest.class.getResource("edge_filtered.png").toString()).getPixelReader();
    applyAllPixels((x, y) -> {
      assertEquals(
              expPReader.getColor(x, y),
              actPReader.getColor(x, y)
      );
    });
  }
  
  @Test
  public void testToImage_evaluate_multi_convolution_function() {
    LatentImage limage = new LatentImage(in);
    limage.transform(new ConvolutionTransformer(new double[][]{{0, -1, 0},
                                                               {-1, 4, -1},
                                                               {0, -1, 0}}));
    
    double ave = 1 / 9.0;
    limage.transform(new ConvolutionTransformer(new double[][]{{ave, ave, ave},
                                                               {ave, ave, ave},
                                                               {ave, ave, ave}}));

    PixelReader actPReader = limage.toImage().getPixelReader();
    PixelReader expPReader = new Image(LatentImageTest.class.getResource("edge_and_smooth_filtered.png").toString()).getPixelReader();
    applyAllPixels((x, y) -> {
      assertEquals(
              expPReader.getColor(x, y),
              actPReader.getColor(x, y)
      );
    });
  }
}