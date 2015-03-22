package chapter8.ex10;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Grep {

//  ##############################################  
//  src.zip の中身はバージョン管理システム上には無い
//  ##############################################
  static final String ROOT = "C:\\Users\\horiuchi\\Desktop\\javaapisrc\\";

  public static void main(String[] args) {
    try (Stream<Path> entries = Files.walk(Paths.get(ROOT))) {
      entries.
              filter(path -> !Files.isDirectory(path)).
              filter((path) -> {
                return fileContains(path, "volatile") && fileContains(path, "transient");
              }).
              forEach(path -> System.out.println(path.toString().substring(ROOT.length())));
    } catch (IOException ex) {
      Logger.getLogger(Grep.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private static boolean fileContains(Path path, String str) {
    try {
      return Files.lines(path).anyMatch(line -> line.contains(str));
    } catch (UncheckedIOException | IOException ex) {
      Logger.getLogger(Grep.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
  }
}
