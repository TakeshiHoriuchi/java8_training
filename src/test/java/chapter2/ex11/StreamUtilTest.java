package chapter2.ex11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class StreamUtilTest {

  @Test
  public void testParallelSetValue() {
    List<String> list = new ArrayList<>(4);
    for (int i=0; i<4; i++) list.add(null);
    
    String[] expects = new String[] {"a", "b", "c", "d"};
    StreamUtil.parallelSetValue(list, Arrays.stream(expects));
    
    for (int i=0; i<4; i++) assertTrue(list.contains(expects[i]));
  }
}