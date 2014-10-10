package chapter1.ex6;

import org.junit.Test;
import static chapter1.ex6.RunnableEx.uncheck;

public class RunnableExTest {
  @Test
  public void testUncheck() {
    new Thread(uncheck(() -> Thread.sleep(100))).start();
  }
}