package chapter1.ex4;

import java.io.File;
import java.util.Arrays;

public class DirectoryUtil {

  public static File[] sort(File[] files) {
    if (Arrays.stream(files).anyMatch(f -> f == null))
      throw new NullPointerException();

    File[] results = Arrays.copyOf(files, files.length);
    Arrays.sort(
            results,
            (file1, file2) -> {
              int whichIsDir = Boolean.compare(file1.isDirectory(), file2.isDirectory()) * -1;
              if (whichIsDir != 0)
                return whichIsDir;
              else
                return file1.toString().compareTo(file2.toString());
            }
    );

    return results;
  }
}
