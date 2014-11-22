package chapter4.ex05;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObservableValueUtilTest {

  @Test
  public void testObserve_with_1_arg() {
    BooleanProperty bp = new SimpleBooleanProperty(false);
    IntegerProperty ip = new SimpleIntegerProperty(1);
    bp.bind(ObservableValueUtil.observe(x -> x.intValue() > 100, ip));
    assertFalse(bp.get());
    ip.setValue(1000);
    assertTrue(bp.get());
  }
  
  @Test
  public void testObserve_with_2_args() {
    BooleanProperty bp = new SimpleBooleanProperty(false);
    IntegerProperty ip1 = new SimpleIntegerProperty(1);
    IntegerProperty ip2 = new SimpleIntegerProperty(1);
    bp.bind(ObservableValueUtil.observe((x, y) -> (x.intValue() + y.intValue()) > 100, ip1, ip2));
    assertFalse(bp.get());
    ip1.setValue(1000);
    assertTrue(bp.get());
  }
}