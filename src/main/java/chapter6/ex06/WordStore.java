package chapter6.ex06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
mergeの場合と比較して、new HashSet<>() が呼び出されるタイミングが、指定されたキーの値が無い場合に限定される
*/
public class WordStore {
  private final ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();
  
  public void read(File[] files) {
    Arrays.stream(files).parallel().forEach(file -> {
      try(Scanner scanner = new Scanner(file)) {
        while (scanner.hasNext()) {
          String str = scanner.next();
          map.computeIfAbsent(str, key -> {
            Set<File> set = new HashSet<>();
            set.add(file);
            return set;
          });
          map.computeIfPresent(str, (key, set) -> {
            set.add(file);
            return set;
          });
        }
      } catch (FileNotFoundException ex) {
        Logger.getLogger(WordStore.class.getName()).log(Level.SEVERE, null, ex);
      }
    });
  }
  
  public ConcurrentHashMap<String, Set<File>> getMap() {
    return map;
  }
}
