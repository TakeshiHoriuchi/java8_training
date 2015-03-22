package chapter8.ex02;

/*
Long.MIN_VALUEを引数にとるとMath.negateExact()は例外を出す。
*/
public class MathTest {
  public static void main(String[] args) {
    try {
      Math.negateExact(Long.MIN_VALUE);
    }
    catch (RuntimeException e) {
      System.err.println(e.getMessage());
    }
  }
}
