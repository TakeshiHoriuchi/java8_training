package chapter2.ex08;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StreamUtil {
  public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
    List<T> list = new LinkedList<>();
    List<T> firstList = first.collect(Collectors.toList());
    List<T> secondList = second.collect(Collectors.toList());
    for (int i=0; i < Math.min(firstList.size(), secondList.size()); i++) {
      list.add(firstList.get(i));
      list.add(secondList.get(i));
    }
    return list.stream();
  }
}
