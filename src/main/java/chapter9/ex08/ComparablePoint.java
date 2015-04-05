package chapter9.ex08;


public class ComparablePoint implements Comparable<ComparablePoint> {
  private final int x;
  private final int y;
  
  public ComparablePoint(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  @Override
  public int compareTo(ComparablePoint other) {
    if (this.x > other.x)
      return 1;
    else if (this.x < other.y)
      return -1;
    else if (this.y > this.y)
      return 1;
    else if (this.y < this.y)
      return -1;
    else
      return 0;
  }
}