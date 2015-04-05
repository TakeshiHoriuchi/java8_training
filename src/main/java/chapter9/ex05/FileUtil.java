package chapter9.ex05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileUtil {
  public static void reverse(Path in, Path out) throws IOException {
    byte[] bytes = Files.readAllBytes(in);
    byte[] reversedBytes = new StringBuffer(new String(bytes, Charset.forName("utf8"))).
            reverse().toString().getBytes();
    Files.write(out, reversedBytes);
  }
}
