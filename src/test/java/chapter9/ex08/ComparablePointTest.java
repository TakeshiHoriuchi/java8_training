package chapter9.ex08;

import org.junit.Test;
import static org.junit.Assert.*;

public class ComparablePointTest {

  @Test
  public void testCompareTo_減算するとオーバーフローする入力でも正しい結果を返す() {
    ComparablePoint p1 = new ComparablePoint(Integer.MAX_VALUE, 0);
    ComparablePoint p2 = new ComparablePoint(-1000, 0);
    assertTrue(p1.compareTo(p2) > 0);
  }
}