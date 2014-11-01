package chapter3.ex02;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentUtil {
  public static void withLock(ReentrantLock myLock, ConcurrentUtil.ThrowableRunnable r) throws Throwable {
    myLock.lock();
    try {
      r.run();
    } finally {
      myLock.unlock();
    }
  }
  
  public interface ThrowableRunnable {
    void run() throws Throwable;
  }
}
