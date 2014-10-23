package chapter2.ex01;

import java.util.concurrent.atomic.AtomicInteger;


public class Counter {
  
  public static void main(String[] args) {
    String[] words = new String[] {
      "12345678901234",
      "1234",
      "123456",
      "12345678901234567890"
    };
 
    AtomicInteger count = new AtomicInteger(0);
    for (String w: words) {
      new Thread(() -> {
        if (w.length() > 12) count.incrementAndGet();
      }).run();
    }
    
    System.out.println(count);
  }
}
