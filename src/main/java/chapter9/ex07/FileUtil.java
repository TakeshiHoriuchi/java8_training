package chapter9.ex07;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileUtil {
  public static void wget(String url, Path path) throws IOException {
    Files.copy(new URL(url).openStream(), path);
  }
}
