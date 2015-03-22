package chapter8.ex06;

import java.util.Comparator;
import java.util.Objects;
import javafx.geometry.Rectangle2D;

/*
面積で比較
*/
public class Rectangle2DComparator implements Comparator<Rectangle2D> {

  @Override
  public int compare(Rectangle2D o1, Rectangle2D o2) {
    Objects.requireNonNull(o1);
    Objects.requireNonNull(o2);
    
    double o1Area = o1.getHeight() * o1.getWidth();
    double o2Area = o2.getHeight() * o2.getWidth();
    
    return (int)(o1Area - o2Area);
  }
}
