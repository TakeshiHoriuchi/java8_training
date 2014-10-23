package chapter2.ex08;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;

public class StreamUtilTest {

  @Test
  public void testZip() {
    String actual = StreamUtil.zip(
            Stream.of("a", "b", "c"),
            Stream.of("1", "2", "3", "4")
    ).collect(Collectors.joining());
    assertEquals("a1b2c3", actual);
  }

}