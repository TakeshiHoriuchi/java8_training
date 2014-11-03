package chapter3.ex11;

import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import org.junit.Test;
import static org.junit.Assert.*;
import org.loadui.testfx.GuiTest;
import static util.TestUtil.forEachIndex;
import static util.TestUtil.getTestImageURL;

public class ColorTransformerTest extends GuiTest {

  @Test
  public void testConvertUnaryOperator() {
    Image in = new Image(getTestImageURL());
    int width = (int) in.getWidth();
    int height = (int) in.getHeight();
    ColorTransformer brighter = ColorTransformer.convertUnaryOperator(c -> c.deriveColor(0, 1, 1.2, 1));
    Image result = ImageUtil.transform(in, brighter);

    PixelReader actPReader = result.getPixelReader();
    PixelReader expPReader = in.getPixelReader();
    forEachIndex(width - 1, x -> {
      forEachIndex(height - 1, y -> {
        assertEquals(
                Math.min(expPReader.getColor(x, y).getBrightness() * 1.2, 1.0),
                actPReader.getColor(x, y).getBrightness(),
                0.01
        );
      });
    });
  }

  @Test
  public void testCompose() {
    Image in = new Image(getTestImageURL());
    int width = (int) in.getWidth();
    int height = (int) in.getHeight();
    ColorTransformer encloser = ColorTransformer.enclose(in, 15, Color.AQUA);
    ColorTransformer brighter = ColorTransformer.convertUnaryOperator(c -> c.deriveColor(0, 1, 1.2, 1));
    Image result = ImageUtil.transform(in, ColorTransformer.compose(encloser, brighter));

    PixelReader actPReader = result.getPixelReader();
    PixelReader expPReader = in.getPixelReader();
    forEachIndex(width - 1, x -> {
      forEachIndex(height - 1, y -> {
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
    });
  }

  @Override
  protected Parent getRootNode() {
    return new MenuBar();
  }
}
