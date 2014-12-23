package chapter5.ex08;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class ZonedDateTimeUtilTest {

  @Test
  public void testGetDateOffsetInAllTimezones_月や年を跨がない場合_キーに含まれているはずの任意の値の一つAsia_Tokyoが含まれており_全ての値の絶対値が1以下であること() {
    Instant instant = LocalDateTime.of(2014, 12, 22, 10, 0).toInstant(ZoneOffset.UTC);
    Map<ZoneId, Integer> result = ZonedDateTimeUtil.getDateOffsetInAllTimezones(instant);
    assertTrue(result.keySet().contains(ZoneId.of("Asia/Tokyo")));
    assertTrue(result.values().stream().allMatch(x -> x == -1 || x == 0 || x == 1));
  }
  
  @Test
  public void testGetDateOffsetInAllTimezones_月を跨ぐ場合_キーに含まれているはずの任意の値の一つAsia_Tokyoが含まれており_全ての値の絶対値が1以下であること() {
    Instant instant = LocalDateTime.of(2014, 12, 1, 3, 0).toInstant(ZoneOffset.UTC);
    Map<ZoneId, Integer> result = ZonedDateTimeUtil.getDateOffsetInAllTimezones(instant);
    assertTrue(result.keySet().contains(ZoneId.of("Asia/Tokyo")));
    assertTrue(result.values().stream().allMatch(x -> x == -1 || x == 0 || x == 1));
  }
  
  @Test
  public void testGetDateOffsetInAllTimezones_年を跨ぐ場合_キーに含まれているはずの任意の値の一つAsia_Tokyoが含まれており_全ての値の絶対値が1以下であること() {
    Instant instant = LocalDateTime.of(2014, 12, 31, 23, 0).toInstant(ZoneOffset.UTC);
    Map<ZoneId, Integer> result = ZonedDateTimeUtil.getDateOffsetInAllTimezones(instant);
    assertTrue(result.keySet().contains(ZoneId.of("Asia/Tokyo")));
    assertTrue(result.values().stream().allMatch(x -> x == -1 || x == 0 || x == 1));
  }
}