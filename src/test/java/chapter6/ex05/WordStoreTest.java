package chapter6.ex05;

import com.google.common.io.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class WordStoreTest {

  @Test
  public void testRead() {
    File[] files = new File[100];
    
    // テストファイル生成
    Stream.iterate(0, x -> x + 1).limit(100).parallel().forEach(i -> {
      
      String words = Stream.generate(() -> {
        return RandomStringUtils.randomAscii(RandomUtils.nextInt(1, 2));
      }).limit(10000).collect(Collectors.joining(" "));
      
      try {
        files[i] = File.createTempFile("test", "");
        Files.write(words, files[i], Charset.defaultCharset());
      } catch (IOException ex) {
        Logger.getLogger(WordStoreTest.class.getName()).log(Level.SEVERE, null, ex);
      }
    });

    // テスト対象実行
    WordStore store = new WordStore();
    store.read(files);
    ConcurrentHashMap<String, Set<File>> map = store.getMap();
    
    // 全てのファイルの全ての単語がmapに保存されていることを確認
    Arrays.stream(files).forEach(file -> {
      try(Scanner scanner = new Scanner(file)) {
        while(scanner.hasNext()) {
          String str = scanner.next();
          assertTrue(map.get(str).contains(file));
        }
      } catch (FileNotFoundException ex) {
        Logger.getLogger(WordStoreTest.class.getName()).log(Level.SEVERE, null, ex);
      }
    });
  }
}
