package chapter5.ex09;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class ZonedDateTimeUtilTest {

  @Test
  public void testGetAllZoneOffsetIncludesInformationSmallerThanHour_全ての値が1時間未満の情報を含んでいること() {
    Map<ZoneId, ZoneOffset> result = ZonedDateTimeUtil.getAllZoneOffsetIncludesInformationSmallerThanHour(Instant.now());
    assertTrue(result.values().stream().allMatch(
            offset -> offset.getTotalSeconds() % 3600 != 0
    ));
  }
}