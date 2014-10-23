package chapter2.ex09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class StreamUtil {
  public static void main(String[] args) {
    List<String> result = null;
    
    result = newStream().reduce((x, y) -> { x.addAll(y); return x; }).get();
    System.out.println(result);
    
    result = newStream().reduce(
            new ArrayList<>(),
            (x, y) -> { x.addAll(y); return x; }
    );
    System.out.println(result);
    
    result = newStream().reduce(
            new ArrayList<>(),
            (x, y) -> { x.addAll(y); return x; },
            (x, y) -> { x.addAll(y); return x; }
    );
    System.out.println(result);
  }
  
  private static <T> ArrayList<T> newArrayList(T... args) {
    return new ArrayList<>(Arrays.asList(args));
  }
  
  private static Stream<ArrayList<String>> newStream() {
    return Stream.of(
            newArrayList("a", "b", "c"), 
            newArrayList("d", "e", "f"), 
            newArrayList("g", "h", "i")
    );
  }
}
