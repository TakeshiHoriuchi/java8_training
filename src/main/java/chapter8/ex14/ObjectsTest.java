package chapter8.ex14;

import java.util.Arrays;
import java.util.Objects;

/*
スタックトレースの最上部がrequireNonNullになるのでNullPointerExceptionが発生したことがわかりやすい？
*/
public class ObjectsTest {
  static void func(String a, String b) {
    if (a == null) throw new NullPointerException();
    Objects.requireNonNull(b);
  }
  
  public static void main(String[] args) {
    try {
      func(null, null);
    } catch (RuntimeException e) {
      System.err.println(Arrays.asList(e.getStackTrace()));
    }
    
    try {
      func("hoge", null);
    } catch (RuntimeException e) {
      System.err.println(Arrays.asList(e.getStackTrace()));
    }
  }
}
