package chapter2.ex02;

import java.util.stream.Stream;


public class FilterTest {
  public static void main(String[] args) {
    Stream.of("12345", "12345", "12345", "12345", "12345", "12345").filter(s -> {
      System.out.println("フィルターメソッド呼び出し");
      return s.length() >= 5;
    }).limit(5).toArray();
  }
}
