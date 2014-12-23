package chapter5.ex09;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.zone.ZoneRulesProvider;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ZonedDateTimeUtil {
  
  public static Map<ZoneId, ZoneOffset> getAllZoneOffsetIncludesInformationSmallerThanHour(Instant instant) {
    return ZoneId.getAvailableZoneIds().stream().
            filter(id -> ZoneId.of(id).getRules().getOffset(instant).getTotalSeconds() % 3600 != 0).
            collect(Collectors.toMap(
                    id -> ZoneId.of(id),
                    id -> ZoneId.of(id).getRules().getOffset(instant)
            ));
  }
}
