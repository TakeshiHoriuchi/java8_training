package chapter8.ex06;

import java.util.Comparator;
import java.util.Objects;
import javafx.geometry.Point2D;


public class Point2DComparator implements Comparator<Point2D> {

  @Override
  public int compare(Point2D o1, Point2D o2) {
    Objects.requireNonNull(o1);
    Objects.requireNonNull(o2);
    
    double xGap = o1.getX() - o2.getX();
    if (xGap != 0.0) 
      return (int)xGap;
    else
      return (int)(o1.getY() - o2.getY());
  }
}
