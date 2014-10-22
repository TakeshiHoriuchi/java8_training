package chapter2.ex01;


public class Counter {
  private static int count = 0;
  
  public static void main(String[] args) {
    Object lockedObj = new Object();
    String[] words = new String[] {"12345678901234", "1234", "123456", "12345678901234567890"};
    for (String w: words) {
      new Thread(() -> {
        if (w.length() > 12) {
          synchronized(lockedObj) {
            count++;
          }
        }
      }).run();
    }
    
    System.out.println(count);
  }
}
