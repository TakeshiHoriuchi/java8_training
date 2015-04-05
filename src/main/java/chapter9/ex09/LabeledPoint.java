package chapter9.ex09;

import java.util.Objects;


public class LabeledPoint {
  private String label;
  private int x;
  private int y;

  public LabeledPoint(String label, int x, int y) {
    this.label = label;
    this.x = x;
    this.y = y;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
  
  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null) return false;
    if (getClass() != other.getClass()) return false;
    LabeledPoint otherP = (LabeledPoint)other;
    return Objects.equals(this.x, otherP.x) && Objects.equals(this.y, otherP.y);
  }
}