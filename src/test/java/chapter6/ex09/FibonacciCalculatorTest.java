package chapter6.ex09;

import org.junit.Test;
import static org.junit.Assert.*;

public class FibonacciCalculatorTest {

  @Test
  public void testCalc() {
    assertEquals(1, FibonacciCalculator.calc(1));
    assertEquals(1, FibonacciCalculator.calc(2));
    assertEquals(8, FibonacciCalculator.calc(6));
    assertEquals(832040, FibonacciCalculator.calc(30)); 
  }
}