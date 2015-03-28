package chapter8.ex15;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileUtilTest {

  @Test
  public void testGrep() throws Exception {
    URI fileUri = FileUtilTest.class.getResource("stub.txt").toURI();
    Path path = Paths.get(fileUri);
    Pattern pattern = Pattern.compile("util");
    List<String> actual = FileUtil.grep(path, pattern);
    assertArrayEquals(
            actual.toArray(new String[0]), 
            new String[] { "import java.util.List;", "import java.util.regex.Pattern;" }
    );
  }
}