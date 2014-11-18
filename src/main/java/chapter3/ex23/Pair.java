package chapter3.ex23;

import java.util.function.Function;


public class Pair<T> extends org.apache.commons.lang3.tuple.MutablePair<T, T> {
  public Pair(T first, T second) { super(first, second); }
  
  public <U> Pair<U> map(Function<T, U> func) {
    return new Pair(func.apply(left), func.apply(right));
  }
}
