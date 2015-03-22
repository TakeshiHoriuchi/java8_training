package chapter8.ex09;

import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScannersTest {

  @Test
  public void testWordsStream() {
    Scanner scanner = new Scanner("foo bar baz");
    String[] actual = Scanners.wordsStream(scanner).toArray(String[]::new);
    assertArrayEquals(new String[] { "foo", "bar", "baz" } , actual);
  }

  @Test
  public void testLinesStream() {
    Scanner scanner = new Scanner("foo bar\nbaz");
    String[] actual = Scanners.linesStream(scanner).toArray(String[]::new);
    assertArrayEquals(new String[] { "foo bar", "baz" } , actual);
  }

  @Test
  public void testIntStream() {
    Scanner scanner = new Scanner("1 2 3 -4");
    int[] actual = Scanners.intStream(scanner).toArray();
    assertArrayEquals(new int[] { 1, 2, 3, -4 } , actual);
  }

  @Test
  public void testDoubleStream() {
    Scanner scanner = new Scanner("1.3 2 -345.678");
    double[] actual = Scanners.doubleStream(scanner).toArray();
    assertArrayEquals(new double[] { 1.3, 2.0, -345.678 } , actual, 0.0);
  }

}