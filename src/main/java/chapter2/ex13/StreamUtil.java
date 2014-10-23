package chapter2.ex13;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtil {
  public static void main(String[] args) {
    Stream<String> stream = Stream.of(
      "12345678901234",
      "1234",
      "123456",
      "5678",
      "12345678901234567890"
    );
 
    Map<Integer, Long> map = stream.parallel().filter(s -> s.length() < 12).
            collect(Collectors.groupingBy(String::length, Collectors.counting()));
    
    System.out.println(map);
  }
}
