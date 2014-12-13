package chapter5.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// 実行結果 (2014/12/01に実行):
//   あなたが生まれてから今日までの日数は9892です
public class SpentDayChecker {
  public static void main(String[] args) {
    long result = check(LocalDate.of(1987, 11, 1));
    System.out.println("あなたが生まれてから今日までの日数は" + result + "です");
  }
  
  public static long check(LocalDate birthday) {
    return birthday.until(LocalDate.now(), ChronoUnit.DAYS);
  }
}
