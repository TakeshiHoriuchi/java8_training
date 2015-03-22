package chapter8.ex06;

import java.util.function.IntPredicate;
import javafx.geometry.Rectangle2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class Rectangle2DComparatorTest {

  @Test
  public void testCompare() {
    int[][][] values = {
      {{3, 3}, {1, 3}},
      {{3, 1}, {3, 3}},
      {{8, 2}, {4, 4}},
    };
    
    IntPredicate[] predicates = {
      i -> i > 0,
      i -> i < 0,
      i -> i == 0
    };
    
    Rectangle2DComparator comparator = new Rectangle2DComparator();
    
    for (int i=0; i < values.length; i++) {
      Rectangle2D r1 = new Rectangle2D(1, 0, values[i][0][0], values[i][0][1]);
      Rectangle2D r2 = new Rectangle2D(33, 1, values[i][1][0], values[i][1][1]);
      int actual = comparator.compare(r1, r2);
      assertTrue(predicates[i].test(actual));
    }
  }
}