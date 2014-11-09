package chapter3.ex12;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import org.junit.Test;
import static org.junit.Assert.*;
import util.ImageTransformTestUtil;
import util.IsEvaluated;

public class LatentImageTest extends ImageTransformTestUtil {
  
  @Test
  public void testTransform_UnaryOperator_not_evaluate_function() {
    LatentImage limage = new LatentImage(in);
    IsEvaluated ise = new IsEvaluated();
    limage.transform((c) -> {ise.isEval = true; return c;});
    assertFalse(ise.isEval);
  }

  @Test
  public void testTransform_ColorTransformer_not_evaluate_function() {
    LatentImage limage = new LatentImage(in);
    IsEvaluated ise = new IsEvaluated();
    limage.transform((x, y, c) -> {ise.isEval = true; return c;});
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

}