package chapter3.ex23;

import org.junit.Test;
import static org.junit.Assert.*;

public class PairTest {

  @Test
  public void testMap() {
    Pair<Integer> pair = new Pair(1, 2);
    Pair<String> actual = pair.map(x -> x.toString());
    
    assertEquals(actual.left, "1");
    assertEquals(actual.right, "2");
  }

}