package chapter5.ex11;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;


public class ZonedDateTimeUtil {
  public static void main(String[] args) {
    ZonedDateTime departure = ZonedDateTime.of(2014, 12, 22, 14, 5, 0, 0, ZoneId.of("CET"));
    ZonedDateTime arrival = ZonedDateTime.of(2014, 12, 22, 16, 40, 0, 0, ZoneId.of("America/Los_Angeles"));
    System.out.println(Duration.between(departure.toInstant(), arrival.toInstant()));
  }
}
