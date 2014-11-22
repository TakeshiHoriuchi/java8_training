package chapter4.ex03;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Greeting {
  
  StringProperty textProperty = null;
  private String textRaw = "default";
  
  public Greeting() { }
  
  public Greeting(String text) {
    this.textRaw = text;
    this.textProperty = new SimpleStringProperty(text);
  }
  
  public final StringProperty textProperty() { 
    if (textProperty == null) textProperty = new SimpleStringProperty(textRaw);
    return textProperty;
  }
  
  public final void setText(String newValue) { 
    if (textProperty != null) textProperty.set(newValue);
    textRaw = newValue;
  }
  
  public final String getText() { return textRaw; }
}
