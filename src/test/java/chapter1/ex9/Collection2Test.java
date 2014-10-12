package chapter1.ex9;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class Collection2Test {

  class C2ArrayList<T> extends ArrayList<T> implements Collection2<T> {}
  
  @Test
  public void testForEachIf() {
    C2ArrayList<String> list = new C2ArrayList<>();
    list.add("foo");
    list.add(null);
    list.add("bar");
    StringBuilder sb = new StringBuilder();
    
    list.forEachIf(
            e -> sb.append(e.toUpperCase()),
            e -> e != null
    );
    
    assertEquals("FOOBAR", sb.toString());
  }
}