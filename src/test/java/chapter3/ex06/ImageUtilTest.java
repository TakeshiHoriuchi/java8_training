package chapter3.ex06;

import java.util.stream.Stream;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import org.junit.Test;
import static org.junit.Assert.*;
import org.loadui.testfx.GuiTest;
import static util.TestUtil.forEachIndex;
import static util.TestUtil.getTestImageURL;

public class ImageUtilTest extends GuiTest {

  @Test
  public void testTransform() {
    Image in = new Image(getTestImageURL());
    int width = (int) in.getWidth();
    int height = (int) in.getHeight();
    Image result = ImageUtil.transform(in, (c, factor) -> c.deriveColor(0, 1, factor, 1), 1.2);

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

  @Override
  protected Parent getRootNode() {
    return new MenuBar();
  }
}