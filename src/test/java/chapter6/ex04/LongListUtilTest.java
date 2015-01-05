package chapter6.ex04;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class LongListUtilTest {

  public List<Long> list;
  
  @Before
  public void setUp() {
    list = Stream.generate(() -> RandomUtils.nextLong(0, Long.MAX_VALUE)).
            limit(1000).collect(Collectors.toList());
  }
  
  @Test
  public void testMax() {
    assertEquals(LongListUtil.max(list), Collections.max(list).longValue());
  }

  @Test
  public void testMin() {
    assertEquals(LongListUtil.min(list), Collections.min(list).longValue());
  }

}