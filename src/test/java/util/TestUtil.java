package util;

import java.io.File;
import java.io.IOException;
import java.util.function.IntConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class TestUtil {

  public static String getTestImageURL() {
    return TestUtil.class.getResource("originalyuruchara.png").toString();
  }
  
  public static void savePNGImage(Image image, String fileName) {
    try {
      ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(fileName));
    } catch (IOException ex) {
      Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public static void forEachIndex(int lastNum, IntConsumer action) {
    IntStream.iterate(0, x -> x + 1).limit(lastNum).forEach(action);
  }
}
