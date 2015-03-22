package chapter8.ex04;

import java.util.Random;
import java.util.stream.LongStream;

// 問題の指示通り計算してもprevを3回呼び出すとき以外0にならない？
public class MathTest {
  public static void main(String[] args) {
    
    long m = 25214903917L;
    
    long min = LongStream.iterate(0, MathTest::prev).limit(1000000).min().getAsLong();
    System.out.println(min ^ m);
    
    long n = prev(0);
    int i = 1;
    for (; i < 100; i++) {
      n = prev(n);
      Random generator = new Random(n ^ m);
      double result = 0.0;
      for (int j = 0; j < i; j++) result = generator.nextDouble();
      System.out.println(result);
    }
  }
  
  private static long prev(long s){
    return (s - 11) * 246154705703781L % (1L << 48);
  }
}
