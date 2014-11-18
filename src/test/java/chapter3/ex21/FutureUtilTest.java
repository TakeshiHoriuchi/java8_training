package chapter3.ex21;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.Test;
import static org.junit.Assert.*;

public class FutureUtilTest {

  @Test
  public void testMap_get_with_no_args() throws InterruptedException, ExecutionException {
    ExecutorService exe = Executors.newSingleThreadExecutor();
    Future<String> future = FutureUtil.map(exe.submit(() -> 1), x -> x.toString());
    
    assertEquals("1", future.get());
    exe.shutdown();
  }

  @Test
  public void testMap_get_with_two_args() throws InterruptedException, ExecutionException, TimeoutException {
    ExecutorService exe = Executors.newSingleThreadExecutor();
    Future<String> future = FutureUtil.map(exe.submit(() -> 1), x -> x.toString());
    
    assertEquals("1", future.get(1, TimeUnit.DAYS));
    exe.shutdown();
  }
}