package chapter6.ex07;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ConcurrentHashMapUtil {
  public static <K, V extends Comparable> K getKeyOfMaxValue(ConcurrentHashMap<K, V> map) {
    Map.Entry<K, V> entry = map.reduceEntries(1000, (oldEntry, newEntry) -> {
      if (oldEntry.getValue().compareTo(newEntry.getValue()) > 0)
        return oldEntry;
      else
        return newEntry;
    });
    return entry.getKey();
  }
}
