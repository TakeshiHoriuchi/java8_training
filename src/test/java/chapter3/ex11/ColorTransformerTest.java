package chapter3.ex11;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import org.junit.Test;
import static org.junit.Assert.*;
import util.ImageTransformTestUtil;

public class ColorTransformerTest extends ImageTransformTestUtil {

  @Test
  public void testFrom() {

    ColorTransformer brighter = ColorTransformer.from(c -> c.deriveColor(0, 1, 1.2, 1));
    Image result = ImageUtil.transform(in, brighter);

    applyAllPixels((x, y) -> {
      assertEquals(
              Math.min(expPReader.getColor(x, y).getBrightness() * 1.2, 1.0),
              result.getPixelReader().getColor(x, y).getBrightness(),
              0.01
      );
    });
  }

  @Test
  public void testCompose() {
    ColorTransformer encloser = ColorTransformer.enclose(in, 15, Color.AQUA);
    ColorTransformer brighter = ColorTransformer.from(c -> c.deriveColor(0, 1, 1.2, 1));
    Image result = ImageUtil.transform(in, ColorTransformer.compose(encloser, brighter));

    PixelReader actPReader = result.getPixelReader();
    applyAllPixels((x, y) -> {
      if (x <= 15 || width - x <= 15 || y <= 15 || height - y <= 15) {
        assertEquals(Color.AQUA, actPReader.getColor(x, y));
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
