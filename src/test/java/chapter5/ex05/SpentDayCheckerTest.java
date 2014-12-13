package chapter5.ex05;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpentDayCheckerTest {

  @Test
  public void testCheck() {
    LocalDate d = LocalDate.now().minusDays(10000);
    long actual = SpentDayChecker.check(d);
    assertEquals(10000, actual);
  }

  @Test
  public void testCheck_returns_minus_value() {
    LocalDate d = LocalDate.now().plusDays(1000);
    long actual = SpentDayChecker.check(d);
    assertEquals(-1000, actual);
  }
}