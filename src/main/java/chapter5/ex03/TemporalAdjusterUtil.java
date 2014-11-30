package chapter5.ex03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TemporalAdjusterUtil {
  public static final int SEARCH_LIMIT = 100000;
  
  /**
   * 返すTemporalAdjusterはSEARCH_LIMIT(100000)日、先まで検索して条件を満たす日が見つからなかった場合、 
   * IllegalArgumentException を throw します。
   */ 
  public static TemporalAdjuster next(Predicate<LocalDate> pred) {
    return TemporalAdjusters.ofDateAdjuster(date -> {
      Optional<LocalDate> opt = Stream.iterate(date, d -> d.plusDays(1)).
              limit(SEARCH_LIMIT).filter(pred).findFirst();
      return opt.orElseThrow(() -> new IllegalArgumentException("cannot find the day satisfy given condition"));
    });
  }
}
