package chapter2.ex05;

import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;

public class StreamUtilTest {

  @Test
  public void testLinearCongruentialRandomStream() {
    long a = 25214903917L;
    long c = 11L;
    long m = power(2, 48);
    long seed = System.nanoTime();
    Stream<Long> result = StreamUtil.linearCongruentialRandomStream(a, c, m, seed);
    result.limit(10).forEach(v -> System.out.println(v));
  }

  private static long power(long a, long b) {
    long result = 1;
    for (int i=1; i <= b; i++) result *= a;
    return result;
  }
}