package chapter6.ex09;

import java.util.Arrays;


public class FibonacciCalculator {
  
  /**
   * n番目のフィボナッチ数を返す。バッファオーバフローは検出しない。
   * @param n 自然数
   * @return n番目のフィボナッチ数 
   * @throws IllegalArgumentException nが自然数で無い場合
   */
  public static long calc(int n) {
    if (n < 1) throw new IllegalArgumentException("The arg must be natural number.");
    if (n == 1) return 1;
    Matrix[] matrixes = new Matrix[n-1];
    Arrays.parallelSetAll(matrixes, i -> new Matrix(1, 1, 1, 0));
    Arrays.parallelPrefix(matrixes, (a, b) -> a.multiply(b));
    return matrixes[n-2].a;
  }
  
  /*
  a b
  c d
  */
  private static class Matrix {
    private final int a, b, c, d;
    
    public Matrix(int a, int b, int c, int d) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
    }
    
    public Matrix multiply(Matrix m) {
      return new Matrix(
              a*m.a + b*m.c, 
              a*m.b + b*m.d, 
              c*m.a + d*m.c, 
              c*m.b + d*m.d
      );
    }
  }
}
