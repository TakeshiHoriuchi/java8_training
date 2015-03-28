package chapter8.ex15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileUtil {
  public static List<String> grep(Path path, Pattern pattern) throws IOException {
    return Files.lines(path).filter(pattern.asPredicate()).collect(Collectors.toList());
  }
}
