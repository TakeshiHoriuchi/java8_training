package chapter3.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * 指定されたフィールドが存在しない場合、アクセスできない場合、Comparableでない場合は無視する。
 * 何も比較対象が無い場合は常に0を返すコンパレータを返す。
 */
public class ComparatorUtil {
  public static <T> Comparator<T> lexicographicComparator(String... fieldNames) {
    return (x, y) -> {
      int result = 0;
      for (String fieldName: fieldNames) {
        try {
          Field f = x.getClass().getField(fieldName);
          result = ((Comparable)f.get(x)).compareTo(f.get(y));
          if (result != 0) return result;
        } catch (Exception ex) {}
      }
      return result;
    };
  }
}
