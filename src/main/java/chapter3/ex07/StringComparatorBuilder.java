package chapter3.ex07;

import java.util.Comparator;


public class StringComparatorBuilder {
  private int order = 1;
  private boolean isCaseSensitive = true;
  private boolean isSpaceSensitive = true;
  
  public StringComparatorBuilder ascending() {
    order = 1;
    return this;
  }
  
  public StringComparatorBuilder descending() {
    order = -1;
    return this;
  }
  
  public StringComparatorBuilder caseSensitive() {
    isCaseSensitive = true;
    return this; 
  }
  
  public StringComparatorBuilder notCaseSensitive() {
    isCaseSensitive = false;
    return this;
  }
  
  public StringComparatorBuilder spaceSensitive() {
    isSpaceSensitive = true;
    return this;
  }
  
  public StringComparatorBuilder notSpaceSensitive() {
    isSpaceSensitive = false;
    return this;
  }
  
  public Comparator<String> build() {
    return (x, y) -> {
      x = isSpaceSensitive ? x : x.replaceAll("\\s+", "");
      y = isSpaceSensitive ? y : y.replaceAll("\\s+", "");
      return (isCaseSensitive ? x.compareTo(y) : x.compareToIgnoreCase(y)) * order;
    };
  }
}
