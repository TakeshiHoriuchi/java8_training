package chapter8.ex12;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.LongStream;


public class Tester {
  
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
  
  public static void main(String[] args) {
    Method[] methods = Tester.class.getMethods();
    
    Arrays.stream(methods).
            filter(m -> m.getAnnotationsByType(TestCase.class).length != 0).
            forEach((method) -> {
              System.out.println("メソッド名： " + method.getName());
              TestCase[] testCases = method.getAnnotationsByType(TestCase.class);
              Arrays.stream(testCases).forEach((testCase) -> {
                try {
                  int params = Integer.parseInt(testCase.params());
                  long expected = Long.parseLong(testCase.expected());
                  long actual = (Long)method.invoke(null, params);
                  System.out.printf(
                          "入力：%d, 期待結果：%d, 結果：%d, 判定：%s%n",
                          params,
                          expected,
                          actual,
                          expected == actual ? "成功" : "失敗"
                  );
                } catch (ReflectiveOperationException | IllegalArgumentException ex) {
                  Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
                }
              });
              System.out.println("");
            });
  }
}
