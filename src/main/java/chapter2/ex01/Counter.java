package chapter2.ex01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Counter {
  
  public static void main(String[] args) throws InterruptedException {
    String[] words = new String[] {
      "12345678901234",
      "1234",
      "123456",
      "12345678901234567890"
    };
 
    AtomicInteger count = new AtomicInteger(0);
    List<Thread> threads = new LinkedList<>();
    for (String w: words) {
      Thread t = new Thread(() -> {
        if (w.length() > 12) count.incrementAndGet();
      });
      t.start();
      threads.add(t);
    }
    for (Thread t: threads) t.join();
    
    System.out.println(count);
  }
}
