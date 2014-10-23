package chapter2.ex06;

import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;

public class StreamUtilTest {

  @Test
  public void testCharacterStream() {
    String testStr = "teststring";
    Stream<Character> s = StreamUtil.characterStream(testStr);
    StringBuilder actualSb = new StringBuilder();
    s.forEach(c -> actualSb.append(c));
    assertEquals(testStr, actualSb.toString());
  }
}