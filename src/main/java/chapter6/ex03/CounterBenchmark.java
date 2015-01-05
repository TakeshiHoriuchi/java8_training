package chapter6.ex03;

import java.time.Duration;
import java.time.Instant;
import java.util.LongSummaryStatistics;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CounterBenchmark {

  private static interface Wrapper {
    void increment();
  }

  private static class AtomicLongWrapper implements Wrapper {
    private final AtomicLong value = new AtomicLong();
    public void increment() { value.getAndIncrement(); }
  }

  private static class LongAdderWrapper implements Wrapper {
    private final LongAdder value = new LongAdder();
    public void increment() { value.increment(); }
  }

  private static Long count(Wrapper wrapper) {
    Instant start = Instant.now();
    Stream.generate(() -> new Thread(() -> { for (int i = 0; i < 100000; i++) wrapper.increment(); })).
            limit(1000).forEach((Thread t) -> t.start());
    Instant end = Instant.now();
    return Duration.between(start, end).toMillis();
  }
  
  private static LongSummaryStatistics benchmark(Supplier<Wrapper> supplier) {
    return Stream.generate(() -> "dummy").limit(10).map(dummy -> count(supplier.get())).
            collect(Collectors.summarizingLong(v -> v));
  }

  public static void main(String[] args) {
    System.out.println("LongAdder: " + benchmark(() -> LongAdderWrapper::new));
    System.out.println("AtomicLong: " + benchmark(() -> AtomicLongWrapper::new));
  }
}
