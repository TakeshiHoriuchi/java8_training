package chapter9.ex05;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileUtilTest {

  @Test
  public void testReverse() throws URISyntaxException, IOException {
    URL inURL = FileUtilTest.class.getResource("in.txt");
    URL outURL = FileUtilTest.class.getResource("out.txt");
    FileUtil.reverse(
            Paths.get(inURL.toURI()),
            Paths.get(outURL.toURI())
    );
    String actual = new String(Files.readAllBytes(Paths.get(outURL.toURI())));
    assertEquals("おえういあcba", actual);
  }
}