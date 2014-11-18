package chapter3.ex21;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;


public class FutureUtil {
  public static <T, U> Future<U> map(Future<T> future, Function<T, U> func) {
    
    return new Future<U>() {
      @Override
      public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
      }

      @Override
      public boolean isCancelled() {
        return future.isCancelled();
      }

      @Override
      public boolean isDone() {
        return future.isDone();
      }

      @Override
      public U get() throws InterruptedException, ExecutionException {
        return func.apply(future.get());
      }

      @Override
      public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return func.apply(future.get(timeout, unit));
      }
    };

//    以下のようにProxyクラスを使って実装できないか試してみたが、エラーが発生した
//
//    return (Future<U>)Proxy.newProxyInstance(
//            Future.class.getClassLoader(), 
//            new Class[] { Future.class }, 
//            (proxy, method, args) -> {
//              if (method.getName().equals("get"))
//                return func.apply((T)method.invoke(proxy, args));
//              else
//                return method.invoke(proxy, args);
//    });
  }
}
