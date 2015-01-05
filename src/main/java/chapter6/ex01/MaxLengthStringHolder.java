package chapter6.ex01;

import java.util.concurrent.atomic.AtomicReference;


public class MaxLengthStringHolder {
  private final AtomicReference<String> strRef = new AtomicReference<>("");
  
  public void setIfLarge(String argStr) {
    strRef.updateAndGet(fieldStr -> {
      return argStr.length() > fieldStr.length() ? argStr : fieldStr;
    });
  }
  
  public String get() {
    return strRef.get();
  }
}
