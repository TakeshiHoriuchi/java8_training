package chapter9.ex06;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;


public class FileUtil {
  public static void reverse(Path in, Path out) throws IOException {
    List<String> lines = Files.readAllLines(in, Charset.forName("utf8"));
    Collections.reverse(lines);
    Files.write(out, lines);
  }
}
