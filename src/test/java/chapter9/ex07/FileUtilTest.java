package chapter9.ex07;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;
import spark.Spark;

public class FileUtilTest {

  @Test
  public void testWget() throws Exception {
    Spark.get("/", (req, res) -> "Fuga");
    Path actualPath = Paths.get("./actual.txt");
    FileUtil.wget("http://localhost:4567/", actualPath);
    String actual = new String(Files.readAllBytes(actualPath));
    assertEquals("Fuga", actual);
  }
}