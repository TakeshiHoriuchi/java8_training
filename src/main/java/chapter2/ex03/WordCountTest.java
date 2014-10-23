package chapter2.ex03;

//parallelStream のほうが遅い？

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class WordCountTest {
  public static void main(String[] args) {
    List<String> words = Stream.generate(() -> {
      return RandomStringUtils.random(RandomUtils.nextInt(1, 21));
    }).limit(1000000).collect(Collectors.toList());
    
    benchmark(words.stream());
    benchmark(words.parallelStream());
  }
  
  private static void benchmark(Stream<String> stream) {
    long startTime = System.nanoTime();
    stream.filter(w -> w.length() >= 12).toArray();
    long endTime = System.nanoTime();
    System.out.println("実行時間: " + TimeUnit.NANOSECONDS.toMillis(endTime - startTime) + " ミリ秒");
  }
}
