package chapter8.ex05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/*
Collection版
実行時間: 27 ミリ秒

Stream版
実行時間: 20 ミリ秒
*/
public class CollectionTest {
 public static void main(String[] args) {
    List<String> words = Stream.generate(() -> {
      return RandomStringUtils.random(RandomUtils.nextInt(1, 21));
    }).limit(1000000).collect(Collectors.toList());
    
    List<String> benched = new ArrayList<>(words);
    benchmark(benched,
              list -> list.removeIf(w -> w.length() < 12));
    benched = new ArrayList<>(words);
    benchmark(benched,
              list -> list.stream().filter(w -> w.length() >= 12).collect(Collectors.toList()));
  }
  
  private static void benchmark(List<String> words, Consumer<? super List<String>> consumer) {
    long startTime = System.nanoTime();
    consumer.accept(words);
    long endTime = System.nanoTime();
    System.out.println("実行時間: " + TimeUnit.NANOSECONDS.toMillis(endTime - startTime) + " ミリ秒");
  }
}
