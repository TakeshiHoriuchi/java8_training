package chapter3.ex22;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class CompletableFutureUtil {

  public static <T, U> CompletableFuture<U> flatMap(CompletableFuture<T> future, Function<T, CompletableFuture<U>> func) {
//    return func.apply(future.get());
    return future.thenComposeAsync(func);
  }

  /*
  出力は
  foo
  bar
  1bazとなる
  */
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    CompletableFuture<Integer> ci = CompletableFuture.supplyAsync(() -> 1);
    CompletableFuture<String> cf = flatMap(
            ci,
            (x) -> {
              try { Thread.sleep(3000); } catch (InterruptedException ex) {}
              System.out.println("bar");
              return CompletableFuture.supplyAsync(() -> x + "baz");
            });
    System.out.println("foo");
    System.out.println(cf.get());
  }
}
