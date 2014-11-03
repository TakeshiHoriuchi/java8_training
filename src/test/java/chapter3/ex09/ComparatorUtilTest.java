package chapter3.ex09;

import java.util.Comparator;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComparatorUtilTest {

  @Test
  public void testLexicographicComparator_noargs() {
    Comparator<StubClass> c = ComparatorUtil.lexicographicComparator();
    assertEquals(0, c.compare(new StubClass(), new StubClass()));
  }
  
  @Test
  public void testLexicographicComparator_twoargs_different_value() {
    Comparator<StubClass> c = ComparatorUtil.lexicographicComparator("firstField", "secondField");
    int result = c.compare(new StubClass("abc", "def"), new StubClass("abc", "abc"));
    assertThat(result, is(greaterThan(0)));
  }
  
  @Test
  public void testLexicographicComparator_twoargs_same_value() {
    Comparator<StubClass> c = ComparatorUtil.lexicographicComparator("firstField", "secondField");
    int result = c.compare(new StubClass("abc", "def"), new StubClass("abc", "def"));
    assertThat(result, is(0));
  }
  
  @Test
  public void testLexicographicComparator_not_exist_field() {
    Comparator<StubClass> c = ComparatorUtil.lexicographicComparator("notField", "firstField");
    int result = c.compare(new StubClass("def", "abc"), new StubClass("abc", "abc"));
    assertThat(result, is(greaterThan(0)));
  }
  
  @Test
  public void testLexicographicComparator_private_field() {
    Comparator<StubClass> c = ComparatorUtil.lexicographicComparator("privateField", "firstField");
    int result = c.compare(new StubClass("def", "abc", "abc"), new StubClass("abc", "abc", "def"));
    assertThat(result, is(greaterThan(0)));
  }
  
  @Test
  public void testLexicographicComparator_not_comparable_field() {
    Comparator<StubClass> c = ComparatorUtil.lexicographicComparator("notComparable");
    int result = c.compare(new StubClass("def", "abc", "abc"), new StubClass("abc", "abc", "def"));
    assertThat(result, is(0));
  }

  public class StubClass {
    public String firstField;
    public String secondField;
    private String privateField;
    public Object notComparable = new Object();
    
    public StubClass() {      
    }

    public StubClass(String firstField, String secondField) {
      this.firstField = firstField;
      this.secondField = secondField;
    }
    
    public StubClass(String firstField, String secondField, String privateField) {
      this.firstField = firstField;
      this.secondField = secondField;
      this.privateField = privateField;
    }
  }
}