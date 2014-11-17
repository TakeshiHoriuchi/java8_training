package chapter3.ex18;

import java.util.function.Function;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class FunctionUtilTest {

  @Test
  public void testUncheckedRegular() {
    Function<String, Integer> f = FunctionUtil.unchecked((String x) -> Integer.parseInt(x));
    assertEquals(123, f.apply("123").intValue());
  }
  
  @Test
  public void testUnchecked_throw_RuntimeException_when_checked_exception_threw() {
    try {
      FunctionUtil.unchecked(x -> { throw new Exception("test message"); }).apply(new Object());
    } catch (RuntimeException e) {
      assertThat(e.getMessage(), is(containsString("test message")));
      return;
    }
    fail("Must return from catch block");
  }
}