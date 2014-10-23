package chapter2.ex12;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class Counter {
  public static void main(String[] args) {
    Stream<String> stream = Stream.of(
      "12345678901234",
      "1234",
      "123456",
      "1234",
      "12345678901234567890"
    );
 
    AtomicInteger[] shortWords = Stream.
            generate(AtomicInteger::new).limit(12).
            toArray(AtomicInteger[]::new);

    stream.parallel().forEach(s -> {
      if (s.length() < 12)
        shortWords[s.length()].incrementAndGet();
    });
    
    for (AtomicInteger a: shortWords)
      System.out.println(a.get());
  }
}
