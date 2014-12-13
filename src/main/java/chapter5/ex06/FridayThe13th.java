package chapter5.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FridayThe13th {

  public static void main(String[] args) {
    System.out.println(allDateInCentury(20));
  }
  
  public static List<LocalDate> allDateInCentury(int century) {
    LocalDate first13th = LocalDate.of(century * 100 - 99, 1, 13);

    return Stream.iterate(first13th, date -> date.plusMonths(1)).
            limit(12 * 100).
            filter(date -> date.getDayOfWeek() == DayOfWeek.FRIDAY).
            collect(Collectors.toList());
  }
}
