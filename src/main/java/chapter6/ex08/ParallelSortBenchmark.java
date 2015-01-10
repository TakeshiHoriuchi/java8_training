package chapter6.ex08;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomUtils;


public class ParallelSortBenchmark {

  public static void main(String[] args) {

    System.out.println("各要素数でソートを10000回繰り返した合計値をミリ秒で表した値を表示する");
    IntStream.of(10, 100, 1000, 10000).forEach(count -> {
      long sortTime = benchmark(count, Arrays::sort);
      long paraTime = benchmark(count, Arrays::parallelSort);
      System.out.printf(
              "要素数: %5d, sort: %5d, parallelSort: %5d, 速いメソッド: %s\n",
              count,
              sortTime,
              paraTime,
              (sortTime < paraTime ? "sort" : "parallelSort")
      );
    });

  }

  private static long benchmark(int count, IntArrayConsumer strategy) {

    int[][] arrays = new int[10000][count];
 
    for (int i = 0; i < 10000; i++) {
      for (int j = 0; j < count; j++) {
        arrays[i][j] = RandomUtils.nextInt(0, Integer.MAX_VALUE);
      }
    }
    Instant startTime = Instant.now();
    for (int[] array : arrays) {
      strategy.accept(array);
    }

    return Duration.between(startTime, Instant.now()).toMillis();
  }

  @FunctionalInterface
  private static interface IntArrayConsumer {

    void accept(int[] array);
  }
}
