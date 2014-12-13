package chapter5.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class FridayThe13thTest {

  @Test
  public void testAllDateInCentury() {
    List<LocalDate> result = FridayThe13th.allDateInCentury(21);
    result.stream().forEach((date) -> {
      assertEquals((date.getYear() + 99) / 100, 21); // date should be in 21 century
      assertEquals(date.getDayOfMonth(), 13);
      assertEquals(date.getDayOfWeek(), DayOfWeek.FRIDAY);
    });
  }

}