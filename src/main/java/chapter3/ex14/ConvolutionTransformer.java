package chapter3.ex14;

import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.Arrays;
import javafx.scene.paint.Color;

public class ConvolutionTransformer {

  private final double[][] filter;

  public ConvolutionTransformer(double[][] filter) {
    if (filter.length != 3 || Arrays.stream(filter).anyMatch(f -> f.length != 3))
      throw new IllegalArgumentException("array must be 3 * 3");
      
    this.filter = filter;
  }

  public BaseColorTransformer toBaseColorTransformer() {
    return (x, y, reader) -> {
      double appliedR = 0;
      double appliedG = 0;
      double appliedB = 0;

      for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
          try {
            Color c = reader.getColor(x + i, y + j);

            double f = filter[i + 1][j + 1];

            appliedR += f * c.getRed();
            appliedG += f * c.getGreen();
            appliedB += f * c.getBlue();
          } catch (IndexOutOfBoundsException e) {
          }
        }
      }

      // Colorのコンストラクタの引数にとり得る値の範囲を超えた場合、とり得る値の範囲内で最も近い値に変換する
      return Color.color(
              max(min(appliedR, 1.0), 0),
              max(min(appliedG, 1.0), 0),
              max(min(appliedB, 1.0), 0));
    };
  }
}
