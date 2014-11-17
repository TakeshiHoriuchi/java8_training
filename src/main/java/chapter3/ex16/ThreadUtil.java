package chapter3.ex16;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

// 利用例はテストに示す
public class ThreadUtil {

  public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second, Consumer<Thread> waiter) {
    Thread t = new Thread(() -> {
      try {
        T result = first.get();
        second.accept(result, null);
      } catch (Throwable ex) {
        second.accept(null, ex);
      }
    });
    t.start();
    waiter.accept(t);
  }
}
