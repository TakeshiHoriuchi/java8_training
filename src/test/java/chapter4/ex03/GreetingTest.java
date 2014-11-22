package chapter4.ex03;

import javafx.beans.property.StringProperty;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class GreetingTest {

  public Greeting g;
  
  @Before
  public void setUp() { g = new Greeting(); }
  
  @Test
  public void testGreeting_called_with_text_arg_creates_textProperty() {
    g = new Greeting("fuga");
    assertEquals(g.getText(), "fuga");
    assertEquals(g.textProperty.get(), "fuga");
  }
  
  @Test
  public void testTextProperty_returns_not_null() {
    StringProperty sp = g.textProperty();
    assertEquals(sp.get(), "default");
  }
  
  @Test
  public void testGetText_textProperty_is_not_created() {
    assertEquals(g.getText(), "default");
    assertEquals(g.textProperty, null);
  }
  
  @Test
  public void testSetText_textProperty_is_not_created() {
    g.setText("foo");
    assertEquals(g.getText(), "foo");
    assertEquals(g.textProperty, null);
  }
  
  @Test
  public void testSetText_called_after_call_textProperty_changes_the_value_of_textProperty() {
    g.textProperty();
    g.setText("foo");
    assertEquals(g.getText(), "foo");
    assertEquals(g.textProperty().get(), "foo");
  }
}