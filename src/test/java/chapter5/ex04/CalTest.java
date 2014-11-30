package chapter5.ex04;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalTest {

  @Test
  public void testCal_when_1st_day_is_monday() {
    String expected = 
            " 1  2  3  4  5  6  7\n" + 
            " 8  9 10 11 12 13 14\n" + 
            "15 16 17 18 19 20 21\n" + 
            "22 23 24 25 26 27 28\n" + 
            "29 30\n";
    assertEquals(expected, Cal.cal("9", "2014"));
  }

  @Test
  public void testCal_when_1st_day_is_sunday() {
    String expected = 
            "                   1\n" +
            " 2  3  4  5  6  7  8\n" +
            " 9 10 11 12 13 14 15\n" +
            "16 17 18 19 20 21 22\n" +
            "23 24 25 26 27 28 29\n" +
            "30 31\n";
    assertEquals(expected, Cal.cal("3", "2015"));
  }
  
  @Test
  public void testCal_when_last_day_is_monday() {
    String expected = 
            "                   1\n" +
            " 2  3  4  5  6  7  8\n" +
            " 9 10 11 12 13 14 15\n" +
            "16 17 18 19 20 21 22\n" +
            "23 24 25 26 27 28 29\n" +
            "30\n";
    assertEquals(expected, Cal.cal("6", "2014"));
  }

  @Test
  public void testCal_when_last_day_is_sunday() {
    String expected = 
            "             1  2  3\n" +
            " 4  5  6  7  8  9 10\n" +
            "11 12 13 14 15 16 17\n" +
            "18 19 20 21 22 23 24\n" +
            "25 26 27 28 29 30 31\n";
    assertEquals(expected, Cal.cal("8", "2014"));
  }
}