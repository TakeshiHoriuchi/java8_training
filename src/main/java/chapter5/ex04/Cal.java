package chapter5.ex04;

import java.time.DayOfWeek;
import java.time.LocalDate;
import static org.apache.commons.lang3.StringUtils.repeat;


public class Cal {
  public static void main(String[] args) {
    System.out.println(cal(args[0], args[1]));
  }

  public static String cal(String month, String year) {
    LocalDate d = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
    int thisMonth = d.getMonthValue();
    StringBuilder result = new StringBuilder();
    
    for( ; d.getMonthValue() == thisMonth; d = d.plusDays(1)) {
      if (d.getDayOfMonth() == 1) {
        int firstPaddingCount = (d.getDayOfWeek().getValue() - 1) * 3;
        result.append(repeat(' ', firstPaddingCount));
        result.append(" 1");
      }
      else if (d.getDayOfWeek() == DayOfWeek.MONDAY) {
        result.append('\n').append(String.format("%2d", d.getDayOfMonth()));
      }
      else {
        result.append(String.format(" %2d", d.getDayOfMonth()));
      }
    }
    
    return result.append('\n').toString();
  }
}
