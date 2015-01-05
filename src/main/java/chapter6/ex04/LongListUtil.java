package chapter6.ex04;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.LongAccumulator;

public class LongListUtil {
  /**
   * @return list内の最大値を返す。listが空リストの場合はLong.MIN_VALUEを返す。 
   * @throws NullPointerException
   */
  public static long max(List<Long> list) {
    Objects.requireNonNull(list);
    LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);
    list.parallelStream().forEach(x -> accumulator.accumulate(x));
    return accumulator.get();
  }
  
  /**
   * @return list内の最小値を返す。listが空リストの場合はLong.MAX_VALUEを返す。 
   * @throws NullPointerException
   */
  public static long min(List<Long> list) {
    Objects.requireNonNull(list);
    LongAccumulator accumulator = new LongAccumulator(Long::min, Long.MAX_VALUE);
    list.parallelStream().forEach(x -> accumulator.accumulate(x));
    return accumulator.get();
  }
}
