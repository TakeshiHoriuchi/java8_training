package chapter3.ex05;

import java.util.stream.Stream;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import org.junit.Test;
import static org.junit.Assert.*;
import org.loadui.testfx.GuiTest;

/*
 * GuiTestを継承せずにImageを初期化しようとすると、
 * Cannot create a JavaFX Image until "Internal graphics" are initialized
 * というエラーが出る。
 */
public class ImageUtilTest extends GuiTest{
  @Test
  public void testTransform() {
    Image in = new Image(getTestImageURL());
    int width = (int) in.getWidth();
    int height = (int) in.getHeight();
    Image result = ImageUtil.transform(in, (x, y, c) -> {
      if (x <= 10 || width - x <= 10 || y <= 10 || height - y <= 10)
        return Color.GRAY;
      else 
        return c;
    });

    PixelReader actPReader = result.getPixelReader();
    PixelReader expPReader = in.getPixelReader();
    Stream.iterate(0, x -> x + 1).limit(width - 1).forEach(x -> {
      Stream.iterate(0, y -> y + 1).limit(height - 1).forEach(y -> {
        if (x <= 10 || width - x <= 10 || y <= 10 || height - y <= 10)
          assertEquals(Color.GRAY, actPReader.getColor(x, y));
        else
          assertEquals(expPReader.getColor(x, y), actPReader.getColor(x, y));
      });
    });
  }

  private String getTestImageURL() {
    return ImageUtilTest.class.getResource("originalyuruchara.png").toString();
  }

  @Override
  protected Parent getRootNode() {
    return new MenuBar();
  }
}
