package chapter3.ex22;

/*
以下のように ComletableFuture のflatMapは定義可能だが、実引数のfutureの完了を待つ必要があるのであまり意味はない
*/

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;


public class CompletableFutureUtil {
  public static <T, U> CompletableFuture<U> flatMap(CompletableFuture<T> future, Function<T, CompletableFuture<U>> func) throws InterruptedException, ExecutionException {
    return func.apply(future.get());
  }
}
