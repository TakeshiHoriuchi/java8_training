package chapter1.ex9;

/*
テストコードにnullでないときのみ、要素のメソッドを実行する利用例を示した。
*/

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Collection2<T> extends Collection<T>{
  default void forEachIf(Consumer<T> action, Predicate<T> filter) {
    forEach(element -> { if (filter.test(element)) action.accept(element); });
  }
}
