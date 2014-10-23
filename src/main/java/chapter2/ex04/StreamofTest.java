package chapter2.ex04;

import java.util.Arrays;
import java.util.stream.Stream;


public class StreamofTest {
  public static void main(String[] args) {
    //配列を of の引数に指定すると配列を1要素として持つStreamができる
    int[] values = {1, 4, 9, 16};
    Stream.of(values).forEach(v -> System.out.println(v.getClass().getName()));
    
    //Arrays.stream で配列内の要素をStreamに変換できる
    Arrays.stream(new int[] {1, 2, 3}).forEach(v -> System.out.println(v));
  }
}
