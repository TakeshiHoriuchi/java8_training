package chapter3.ex02;

import java.util.concurrent.locks.ReentrantLock;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static chapter3.ex02.ConcurrentUtil.withLock;

public class ConcurrentUtilTest {

  ReentrantLock myLock;
  StringBuilder sb;
  
  @Before
  public void setUp() {
     myLock = new ReentrantLock();
     sb = new StringBuilder();
  }

  @Test
  public void testWithLock_execute_function() throws Throwable {
    withLock(myLock, () -> sb.append("foo"));
    assertEquals(sb.toString(), "foo");
    assertFalse(myLock.isLocked());
  }
  
  @Test
  public void testWithLock_get_lock_and_execute_function() throws InterruptedException {
    myLock.lock();
    Thread t = new Thread(() -> {
      try {
        withLock(myLock, () -> {
          System.out.println("fuga");
          sb.append("bar");
        });
      } catch (Throwable ex) {}
    });
    t.start();
    Thread.sleep(1000);
    sb.append("foo");
    myLock.unlock();
    t.join();
    assertEquals("foobar", sb.toString());
    assertFalse(myLock.isLocked());
  }
  
  @Test
  public void testWithLock_when_function_throws_exception_then_myLock_is_unlocked() {
    try {
      withLock(myLock, () -> {throw new Exception();});
    } catch (Throwable e) {}
    assertFalse(myLock.isLocked());
  }
}