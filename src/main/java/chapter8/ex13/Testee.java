package chapter8.ex13;

import java.util.stream.LongStream;


public class Testee {
  @TestCase(params = "4", expected = "24")
  @TestCase(params = "0", expected = "1")
  public static long factorial(int n) {
    if (n <= 0) return 1;
    return LongStream.iterate(1, x -> x + 1).limit(n).reduce((a, b) -> a * b).getAsLong();
  }
  
  @TestCase(params = "4", expected = "24")
  public static long factorial2(int n) {
    if (n <= 0) return 1;
    return LongStream.iterate(1, x -> x + 1).limit(n).reduce((a, b) -> a * b).getAsLong();
  }
}
