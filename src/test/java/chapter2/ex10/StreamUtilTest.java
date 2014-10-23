package chapter2.ex10;

import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;

public class StreamUtilTest {

  @Test
  public void testAvg() {
    assertEquals(7.5, StreamUtil.avg(Stream.of(2.0, 4.0, 8.0, 16.0)), 0.0);
  }

}