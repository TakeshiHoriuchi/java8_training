package chapter6.ex07;

import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConcurrentHashMapUtilTest {

  @Test
  public void testGetKeyOfMaxValue() {
    ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
    map.put("a", Long.valueOf(1));
    map.put("b", Long.valueOf(2));
    map.put("c", Long.valueOf(3));
    
    assertEquals(ConcurrentHashMapUtil.getKeyOfMaxValue(map), "c");
  }

}