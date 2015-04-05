package chapter9.ex06;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

// 改行コード問題でWindowsでしか成功しない

public class FileUtilTest {

  @Test
  public void testReverse() throws Exception {
    URL inURL = FileUtilTest.class.getResource("in.txt");
    URL outURL = FileUtilTest.class.getResource("out.txt");
    FileUtil.reverse(
            Paths.get(inURL.toURI()),
            Paths.get(outURL.toURI())
    );
    String actual = new String(Files.readAllBytes(Paths.get(outURL.toURI())), Charset.forName("utf8"));
    assertEquals("abc\r\nあいうえお\r\n", actual);
  }
}