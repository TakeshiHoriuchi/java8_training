package chapter3.ex01;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * このインタフェースをimplementsしてgetLoggerを実装すれば、そのクラスでlogIfが使えるようになる。
 * という使われ方を意図している。
 * Java の使用上 getLogger を protected にできないので、
 * おそらくインタフェースの使い方としては間違っている。
 */
public interface LoggerUtil {
  Logger getLogger();
  
  /**
   * level がログを出力する閾値未満のときbs, sは評価されない
   * bsが偽のときsは評価されない
   * @param level ログレベル
   * @param bs ログを出力する条件の真偽値を返す関数
   * @param s 出力するログを返す関数
   */
  default void logIf(Level level, BooleanSupplier bs, Supplier<String> s) {
    if (getLogger().isLoggable(level) && bs.getAsBoolean())
      getLogger().log(level, s);
  }
}
