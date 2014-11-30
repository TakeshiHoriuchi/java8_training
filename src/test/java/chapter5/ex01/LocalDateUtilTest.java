package chapter5.ex01;

import java.time.LocalDate;
import java.time.Month;
import javax.print.attribute.standard.DateTimeAtCompleted;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocalDateUtilTest {

  @Test
  public void testGetProgramersDay_when_not_leap_year_then_it_returns_9_13() {
    LocalDate d = LocalDateUtil.getProgramersDay(2014);
    assertEquals(LocalDate.of(2014, 9, 13), d);
  }
  
  @Test
  public void testGetProgramersDay_when_leap_year_then_it_returns_9_12() {
    LocalDate d = LocalDateUtil.getProgramersDay(2016);
    assertEquals(LocalDate.of(2016, 9, 12), d);
  }
}