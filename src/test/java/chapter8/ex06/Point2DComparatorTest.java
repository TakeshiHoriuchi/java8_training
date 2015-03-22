package chapter8.ex06;

import java.util.function.IntPredicate;
import javafx.geometry.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class Point2DComparatorTest {

  @Test
  public void testCompare() {
    int[][][] values = {
      {{1, 1}, {0, 1}},
      {{0, 1}, {1, 1}},
      {{0, 1}, {0, 0}},
      {{0, 0}, {0, 1}},
      {{0, 0}, {0, 0}}
    };
    
    IntPredicate[] predicates = {
      i -> i > 0,
      i -> i < 0,
      i -> i > 0,
      i -> i < 0,
      i -> i == 0
    };
    
    Point2DComparator comparator = new Point2DComparator();
    
    for (int i=0; i < values.length; i++) {
      Point2D p1 = new Point2D(values[i][0][0], values[i][0][1]);
      Point2D p2 = new Point2D(values[i][1][0], values[i][1][1]);
      int actual = comparator.compare(p1, p2);
      assertTrue(predicates[i].test(actual));
    }
  }
}