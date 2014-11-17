package chapter3.ex18;

import java.util.function.Function;


public class FunctionUtil {
  public static <T, U> Function<T, U> unchecked(ThrowableFunction<T, U> t) {
    return (T x) -> {
      try {
        return t.apply(x);
      } 
      catch (Exception e) {
        throw new RuntimeException(e);
      } 
    }; 
  }
  
  @FunctionalInterface
  public static interface ThrowableFunction<T, U> {
    U apply(T t) throws Exception;
  }
}
