package chapter3.ex07;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.function.IntPredicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import static util.TestUtil.forEachIndex;

// Parameterized Runner を使うべき

public class StringComparatorBuilderTest {

  IntPredicate isPlus = (x) -> x > 0;
  IntPredicate isMinus = (x) -> x < 0;
  IntPredicate isZero = (x) -> x == 0;

  String[][] TEST_PATTERNS = {
    {"test", "test"},
    {"TEST", "test"},
    {"abc", "de"},
    {" ab  c", "abc"},
    {"a  B c", "a b  c"}
  };

  /*
  上から順番に、以下の場合の期待値を格納する
  
  オーダー | 大文字小文字 | 空白
  -----------------------------
  普通     | 区別する     | 含める
  普通     | 区別する     | 含めない
  普通     | 区別しない   | 含める
  普通     | 区別しない   | 含めない
  逆順     | 区別する     | 含める
  逆順     | 区別する     | 含めない
  逆順     | 区別しない   | 含める
  逆順     | 区別しない   | 含めない
  */
  IntPredicate[][][][] EXPECTS = {
    {
      {
        {isZero, isMinus, isMinus, isMinus, isMinus},
        {isZero, isMinus, isMinus, isZero, isMinus}
      },
      {
        {isZero, isZero, isMinus, isMinus, isMinus},
        {isZero, isZero, isMinus, isZero, isZero}
      }
    },
    {
      {
        {isZero, isPlus, isPlus, isPlus, isPlus},
        {isZero, isPlus, isPlus, isZero, isPlus}
      },
      {
        {isZero, isZero, isPlus, isPlus, isPlus},
        {isZero, isZero, isPlus, isZero, isZero}
      }
    }
  };

  @Test
  public void testBuild() {
    String[] orderOptions = {"ascending", "descending", null};
    String[] caseOptions = {"caseSensitive", "notCaseSensitive", null};
    String[] spaceOptions = {"spaceSensitive", "notSpaceSensitive", null};

    forEachIndex(orderOptions.length, i -> {
      forEachIndex(caseOptions.length, j -> {
        forEachIndex(spaceOptions.length, k -> {

          StringComparatorBuilder builder = new StringComparatorBuilder();
          invokeOptionMethod(builder, orderOptions[i]);
          invokeOptionMethod(builder, caseOptions[j]);
          invokeOptionMethod(builder, spaceOptions[k]);

          Comparator<String> comparator = builder.build();

          forEachIndex(TEST_PATTERNS.length, h -> {
            int result = comparator.compare(TEST_PATTERNS[h][0], TEST_PATTERNS[h][1]);
            assertTrue(EXPECTS[i % 2][j % 2][k % 2][h].test(result)); // i,j,k == 2 のときはデフォルト設定時の期待値を取得する
          });
        });
      });
    });
  }

  private void invokeOptionMethod(StringComparatorBuilder scb, String methodName) {
    if (methodName == null) return;

    try {
      Method m = StringComparatorBuilder.class.getMethod(methodName);
      m.invoke(scb);
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
      Logger.getLogger(StringComparatorBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
