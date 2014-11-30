package chapter5.ex01;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;


public class LocalDateUtil {
  public static LocalDate getProgramersDay(int year) {
    return LocalDate.of(year, Month.JANUARY, 1).plus(255, ChronoUnit.DAYS);
  }
}
