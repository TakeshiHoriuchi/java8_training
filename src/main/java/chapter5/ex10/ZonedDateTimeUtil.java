package chapter5.ex10;

import java.time.ZoneId;
import java.time.ZonedDateTime;


public class ZonedDateTimeUtil {
  public static void main(String[] args) {
    ZonedDateTime departure = ZonedDateTime.of(2014, 12, 22, 3, 5, 0, 0, ZoneId.of("America/Los_Angeles"));
    ZonedDateTime arrival = departure.plusHours(10).plusMinutes(50).withZoneSameInstant(ZoneId.of("CET"));
    System.out.println(arrival);
  }
}
