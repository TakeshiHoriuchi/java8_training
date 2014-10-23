package chapter2.ex10;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/*
count() と sum() はともに終端操作なので同じStreamで両方実行することはできない
*/

public class StreamUtil {
  public static double avg(Stream<Double> stream) {
    AtomicInteger i = new AtomicInteger(0);
    double sum = stream.parallel().
            peek(e -> i.incrementAndGet()).
            reduce((x, y) -> x + y).get();
    return sum / i.get();
  }
}
