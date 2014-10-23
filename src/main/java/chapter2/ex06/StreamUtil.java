package chapter2.ex06;

import java.util.stream.Stream;


public class StreamUtil {
  public static Stream<Character> characterStream(String s) {
    return Stream.iterate(0, v -> v + 1).limit(s.length()).map(i -> s.charAt(i));
  }
}
