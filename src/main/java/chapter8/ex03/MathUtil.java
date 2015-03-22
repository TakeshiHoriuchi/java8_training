package chapter8.ex03;

/*
第二引数が0の場合、%であればそのまま返せばよいので最も実装が簡単
*/
public class MathUtil {

  static class PercentImpl implements GcdStrategy {
    public int gcd(int a, int b) {
      return (b == 0) ? a : gcd(b, a % b);
    }
  }

  static class FloorModImpl implements GcdStrategy {
    public int gcd(int a, int b) {
      return (b == 0) ? a : gcd(b, Math.floorMod(a, b));
    }
  }

  static class RemImpl implements GcdStrategy {
    public int gcd(int a, int b) {
      return (b == 0) ? Math.abs(a) : gcd(b, rem(a, b));
    }

    int rem(int a, int b) {
      return Math.abs(a % b);
    }
  }

  @FunctionalInterface
  public interface GcdStrategy {
    int gcd(int a, int b);
  }

  public static void main(String[] args) {
    String[] desc = {
      "a: +, b: +", "a: +, b: 0", "a: +, b: -",
      "a: 0, b: +", "a: 0, b: 0", "a: 0, b: -",
      "a: -, b: +", "a: -, b: 0", "a: -, b: -"
    };
    int[] aValues = {
      10, 10, 10,
      0, 0, 0,
      -10, -10, -10
    };
    int[] bValues = {
      3, 0, -3,
      3, 0, -3,
      3, 0, -3
    };

    GcdStrategy percentImpl = new PercentImpl();
    GcdStrategy floorModImpl = new FloorModImpl();
    GcdStrategy remImpl = new RemImpl();
    
    System.out.printf("%10s  %10s  %10s  %10s%n", "", "percent", "floormod", "rem");
    for (int i = 0; i < 9; i++) {
      int a = aValues[i];
      int b = bValues[i];
      System.out.printf(
              "%10s  %10d  %10d  %10d%n",
              desc[i],
              percentImpl.gcd(a, b),
              floorModImpl.gcd(a, b),
              remImpl.gcd(a, b)
      );
    }
  }
}
