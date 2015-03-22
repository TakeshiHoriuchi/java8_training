package chapter8.ex07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ComparatorTest {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("b");
    list.add(null);
    list.add("a");
    
    List<String> sorted;
    
    sorted = new ArrayList<>(list);
    sorted.sort(Comparator.nullsLast(Comparator.reverseOrder()));
    System.out.println(sorted);
    
//    sorted = new ArrayList<>(list);
//    Comparator<String> c = Comparator.nullsFirst(Comparator.naturalOrder());
//    c = c.reversed();
//    sorted.sort(c);
//    System.out.println(sorted);
    
  }
}
