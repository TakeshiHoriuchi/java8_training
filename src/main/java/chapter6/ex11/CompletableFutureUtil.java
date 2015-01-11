package chapter6.ex11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class CompletableFutureUtil {
  public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
    return CompletableFuture.supplyAsync(() -> {
      final T t = action.get();
      // until関数も非同期に実行されるべきです
      CompletableFuture<Boolean> cf = CompletableFuture.supplyAsync(() -> until.test(t));
      try {
        return cf.get() ? t : repeat(action, until).get();
      } catch (InterruptedException | ExecutionException ex) {
        throw new RuntimeException(ex);
      }
    });
  }
}
