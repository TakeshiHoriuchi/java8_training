package chapter5.ex03;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

public class TemporalAdjusterUtilTest {

  @Test
  public void testNext_success() {
    LocalDate actual = LocalDate.of(2014, 11, 29).
            with(TemporalAdjusterUtil.next(
                    d -> d.getDayOfWeek().getValue() < 6)
            );
    assertEquals(LocalDate.of(2014, 12, 1), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNext_returns_TemporalAdjuster_that_throws_IlleagalArgumentException_when_not_find_given_days() {
    LocalDate actual = LocalDate.of(2014, 11, 29).
            with(TemporalAdjusterUtil.next(
                    d -> d.getYear() == 2013)
            );
  }
}