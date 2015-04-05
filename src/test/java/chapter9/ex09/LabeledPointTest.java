package chapter9.ex09;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import org.junit.Test;
import static org.junit.Assert.*;

public class LabeledPointTest {
  
  @Test
  public void testHashCode_二つのLabeledPointでyが違うと違うハッシュコードが返る() {
    LabeledPoint lp1 = new LabeledPoint("hoge", 10, 10);
    LabeledPoint lp2 = new LabeledPoint("hoge", 10, 20);
    assertThat(lp1.hashCode(), not(lp2.hashCode()));
  }
  
  @Test
  public void testHashCode_二つのLabeledPointでラベルが異なっていても座標が同じなら同じハッシュコードが返る() {
    LabeledPoint lp1 = new LabeledPoint("hoge", 10, 10);
    LabeledPoint lp2 = new LabeledPoint("fuga", 10, 10);
    assertThat(lp1.hashCode(), is(lp2.hashCode()));
  }
  
  @Test
  public void testEquals_同じインスタンスならtrue() {
    LabeledPoint lp = new LabeledPoint(null, 1, 2);
    assertTrue(lp.equals(lp));
  }
  
  @Test
  public void testEquals_異なるクラスならfalse() {
    LabeledPoint lp = new LabeledPoint(null, 1, 2);
    assertFalse(lp.equals(new Object()));
  }
  
  @Test
  public void testEquals_nullならfalse() {
    LabeledPoint lp = new LabeledPoint(null, 1, 2);
    assertFalse(lp.equals(null));
  }
  
  @Test
  public void testEquals_xとyが等しければラベルが異なっていてもtrue() {
    LabeledPoint lp1 = new LabeledPoint("hoge", 10, 10);
    LabeledPoint lp2 = new LabeledPoint("fuga", 10, 10);
    assertTrue(lp1.equals(lp2));
  }
  
  @Test
  public void testEquals_xが異なっていればfalse() {
    LabeledPoint lp1 = new LabeledPoint("hoge", 12, 10);
    LabeledPoint lp2 = new LabeledPoint("fuga", 10, 10);
    assertFalse(lp1.equals(lp2));
  }
}
