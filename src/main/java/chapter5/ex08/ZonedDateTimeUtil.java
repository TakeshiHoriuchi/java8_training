package chapter5.ex08;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ZonedDateTimeUtil {

  public static void main(String[] args) {
    System.out.println(getDateOffsetInAllTimezones(Instant.now()));
  }

  public static Map<ZoneId, Integer> getDateOffsetInAllTimezones(Instant instant) {
    LocalDate baseDate = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC")).toLocalDate();

    return availableZoneIds().collect(Collectors.toMap(
            id -> id,
            id -> baseDate.until(ZonedDateTime.ofInstant(instant, id).toLocalDate()).getDays()
    ));
  }

  private static Stream<ZoneId> availableZoneIds() {
    return ZoneId.getAvailableZoneIds().stream().map(str -> ZoneId.of(str));
  }
}
