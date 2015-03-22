package chapter8.ex08;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class CheckedQueueTest {

  public static void main(String[] args) {
    Queue<Path> q = new LinkedList<>();
    q = Collections.checkedQueue(q, Path.class);
    try {
      addSomeValue(q);
      q.peek();
    } catch (RuntimeException e) {
      System.err.println(e);
    }
  }

  private static void addSomeValue(Queue q) {
    // ここでClassCastExceptionをスロー
    q.add("hoge");
  }
}
