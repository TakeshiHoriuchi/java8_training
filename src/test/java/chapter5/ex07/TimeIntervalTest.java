package chapter5.ex07;

import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TimeIntervalTest {


  @Test
  public void testBetween_第一引数の時刻が第二引数の時刻より前なら受け付ける() {
    TimeInterval.between(LocalDateTime.MIN, LocalDateTime.MAX);
  }
  
  @Test(expected = RuntimeException.class)
  public void testBetween_第一引数の時刻が第二引数の時刻と同じなら例外を返す() {
    TimeInterval.between(LocalDateTime.MIN, LocalDateTime.MIN);
  }
  
  @Test(expected = RuntimeException.class)
  public void testBetween_第一引数の時刻が第二引数の時刻より後なら例外を返す() {
    TimeInterval.between(LocalDateTime.MAX, LocalDateTime.MIN);
  }
  
  /**
   * 以下のテストではコメントで、
   * メソッドを呼び出されるインスタンスの時刻の範囲と
   * 引数のインスタンスの時刻の範囲の関係を、
   * AAで示す。
   */
  
  private TimeInterval baseInterval;
  private LocalDateTime baseStart;
  private LocalDateTime baseEnd;
  
  @Before
  public void setUp() {
    baseStart = LocalDateTime.of(2014, 12, 14, 7, 0);
    baseEnd = LocalDateTime.of(2014, 12, 14, 8, 30);
    baseInterval = TimeInterval.between(baseStart, baseEnd);
  }
  
  /*
  -----
  -----
  */
  @Test
  public void testAbuts_true1() {
    assertTrue(baseInterval.abuts(baseInterval));
  }
  
  /*
  -----
   ---
  */
  @Test
  public void testAbuts_true2() {
    assertTrue(baseInterval.abuts(createArg(10, -10)));
  }
  
  /*
  -----
   ------
  */
  @Test
  public void testAbuts_true3() {
    assertTrue(baseInterval.abuts(createArg(10, 10)));
  }

  /*
    ----
   -------
  */
  @Test
  public void testAbuts_true4() {
    assertTrue(baseInterval.abuts(createArg(-10, 10)));
  }
  
  /*
    -----
  -----
  */
  @Test
  public void testAbuts_true5() {
    assertTrue(baseInterval.abuts(createArg(-10, -10)));
  }
  
  /*
  ---
      ----
  */
  @Test
  public void testAbuts_false1() {
    assertFalse(baseInterval.abuts(createArg(200, 200)));
  }
  
  /*
  ---
     ----
  */
  @Test
  public void testAbuts_false2() {
    TimeInterval arg = TimeInterval.between(baseEnd, baseEnd.plusMinutes(10));
    assertFalse(baseInterval.abuts(arg));
  }
  
  /*
       -----
  ---
  */
  @Test
  public void testAbuts_false3() {
    assertFalse(baseInterval.abuts(createArg(-200, -200)));
  }
  
  /*
      ---
  ----
  */
  @Test
  public void testAbuts_false4() {
    TimeInterval arg = TimeInterval.between(baseStart.plusMinutes(-10), baseStart);
    assertFalse(baseInterval.abuts(arg));
  }
  
  // baseInterval始点と終点に、引数のminutes分それぞれ加算したインスタンスを返す
  private TimeInterval createArg(int start, int end) {
    return TimeInterval.between(baseStart.plusMinutes(start), baseEnd.plusMinutes(end));
  }
}