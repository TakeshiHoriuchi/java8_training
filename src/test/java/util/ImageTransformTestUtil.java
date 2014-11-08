package util;

import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import org.junit.Before;
import org.loadui.testfx.GuiTest;
import static util.TestUtil.forEachIndex;
import static util.TestUtil.getTestImageURL;


public class ImageTransformTestUtil extends GuiTest {
  protected Image in;
  protected int width;
  protected int height;
  protected PixelReader expPReader;
  
  @Before
  public void setUpStubs() {
    in = new Image(getTestImageURL());
    width = (int) in.getWidth();
    height = (int) in.getHeight();
    expPReader = in.getPixelReader();
  }
  
  @Override
  protected Parent getRootNode() {
    return new MenuBar();
  }
  
  protected void applyAllPixels(IntBiConsumer consumer) {
    forEachIndex(width - 1, x -> {
      forEachIndex(height - 1, y -> {
        consumer.accept(x, y);
      });
    });
  }
  
  @FunctionalInterface
  public interface IntBiConsumer {
    void accept(int x, int y);
  }
}
