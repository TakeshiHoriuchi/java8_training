package chapter8.ex01;

/*
除算、剰余の演算はアルゴリズム中におそらく右シフトが入るので、論理シフトの場合と算術シフトの場合で結果が異なる。
よって、divideUnsignedとremainderUnsignedは必要。
*/
public class IntCalc {
  public static void main(String[] args) {
    int a = Integer.MAX_VALUE;

    System.out.printf("a = Integer.MAX_VALUE: %d%n", a);
    a += 100;
    System.out.printf("a += 100: %d%n", Integer.toUnsignedLong(a));
    a -= Integer.MAX_VALUE / 2;
    System.out.printf("a -= Integer.MAX_VALUE / 2: %d%n", Integer.toUnsignedLong(a));
    a *= 3;
    System.out.printf("a *= 3: %d%n", Integer.toUnsignedLong(a));
    
    int b = 0;
    b = a / 3;
    System.out.printf("b = a / 3: %d%n", b);
    b = Integer.divideUnsigned(a, 3);
    System.out.printf("b = Integer.divideUnsigned(a, 3): %d%n", b);
    b = a % 7;
    System.out.printf("b = a %% 7: %d%n", b);
    b = Integer.remainderUnsigned(a, 7);
    System.out.printf("b = Integer.remainderUnsigned(a, 7): %d%n", b);
  }
}
