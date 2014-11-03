package util;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class TestUtil {

  public static String getTestImageURL() {
    return TestUtil.class.getResource("originalyuruchara.png").toString();
  }
  
  public static void forEachIndex(int lastNum, IntConsumer action) {
    IntStream.iterate(0, x -> x + 1).limit(lastNum).forEach(action);
  }
}
