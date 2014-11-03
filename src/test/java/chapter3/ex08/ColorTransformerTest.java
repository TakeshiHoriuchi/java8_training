package chapter3.ex08;

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
  public void testEnclose() {
    Image in = new Image(getTestImageURL());
    int width = (int) in.getWidth();
    int height = (int) in.getHeight();
    Image result = ImageUtil.transform(in, ColorTransformer.enclose(in, 10, Color.AQUA));

    PixelReader actPReader = result.getPixelReader();
    PixelReader expPReader = in.getPixelReader();
    forEachIndex(width - 1, x -> {
      forEachIndex(height - 1, y -> {
        if (x <= 10 || width - x <= 10 || y <= 10 || height - y <= 10)
          assertEquals(Color.AQUA, actPReader.getColor(x, y));
        else
          assertEquals(expPReader.getColor(x, y), actPReader.getColor(x, y));
      });
    });
  }

  @Override
  protected Parent getRootNode() {
    return new MenuBar();
  }
}