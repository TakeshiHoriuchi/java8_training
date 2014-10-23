package chapter2.ex11;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class StreamUtil {
  public static <T> void parallelSetValue(List<T> list, Stream<T> stream) {
    AtomicInteger ai = new AtomicInteger(-1);
    stream.parallel().forEach(v -> list.set(ai.incrementAndGet(), v));
  }
}
