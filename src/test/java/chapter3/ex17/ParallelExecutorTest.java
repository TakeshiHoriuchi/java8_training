package chapter3.ex17;

import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ParallelExecutorTest {
  ParallelExecutor executor;
  Mock mock1;
  Mock mock2;
  Mock mockHandler;
  
  @Before
  public void setUp() {
    executor = new ParallelExecutor();
    mock1 = new Mock();
    mock2 = new Mock();
    mockHandler = new Mock();
  }

  @Test
  public void testDoInParallelAsyncSuccess() {
    executor.doInParallelAsync(
            () -> mock1.isTouched = true,
            () -> mock2.isTouched = true,
            (ex) -> mockHandler.isTouched = true);
    executor.joinAll();
    assertEquals(true, mock1.isTouched);
    assertEquals(true, mock2.isTouched);
    assertEquals(false, mockHandler.isTouched);
  }
  
  @Test
  public void testDoInParallelAsync_thread1_throws_exception() {
    executor.doInParallelAsync(
            () -> { throw new RuntimeException(); },
            () -> mock2.isTouched = true,
            (ex) -> mockHandler.isTouched = true);
    executor.joinAll();
    assertEquals(false, mock1.isTouched);
    assertEquals(true, mock2.isTouched);
    assertEquals(true, mockHandler.isTouched);
  }
  
  @Test
  public void testDoInParallelAsync_thread2_throws_exception() {
    executor.doInParallelAsync(
            () -> mock1.isTouched = true,
            () -> { throw new RuntimeException(); },
            (ex) -> mockHandler.isTouched = true);
    executor.joinAll();
    assertEquals(true, mock1.isTouched);
    assertEquals(false, mock2.isTouched);
    assertEquals(true, mockHandler.isTouched);
  }
  
  @Test
  public void testDoInParallelAsync_double_thread_throws_exception() {
    StringBuilder sb = new StringBuilder();
    executor.doInParallelAsync(
            () -> { throw new RuntimeException("ex1"); },
            () -> { throw new RuntimeException("ex2"); },
            (ex) -> {
              synchronized(sb) {
                sb.append(ex.getMessage());
              }
            });
    executor.joinAll();
    assertEquals(false, mock1.isTouched);
    assertEquals(false, mock2.isTouched);
    assertThat(sb.toString(), either(equalTo("ex1ex2")).or(equalTo("ex2ex1")));
  }

  class Mock {
    boolean isTouched = false;
  }
}