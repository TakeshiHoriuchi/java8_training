package chapter2.ex05;

import java.util.stream.Stream;


public class StreamUtil {
  public static Stream<Long> linearCongruentialRandomStream(long a, long c, long m, long seed) {
    return Stream.iterate(seed, x -> (a * x + c) % m);
  }
}
