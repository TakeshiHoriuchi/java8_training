package chapter3.ex20;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListUtilTest {

  @Test
  public void testMap() {
    List<String> actual = ListUtil.map(Arrays.asList(1, 2, 3), x -> x.toString());
    List<String> expected = Arrays.asList("1", "2", "3");

    assertThat(actual, is(expected));
  }

}
