package chapter1.ex7;

import java.util.Arrays;

public class RunnableTest {
  public static Runnable andThen(Runnable... runnables) {
    return () -> { Arrays.stream(runnables).forEach(r -> r.run()); };
  }

  public static void main(String[] args) {
    new Thread(
      andThen(
        () -> {System.out.println("foo");}, 
        () -> {System.out.println("bar");}
      )
    ).start();
  }
}
