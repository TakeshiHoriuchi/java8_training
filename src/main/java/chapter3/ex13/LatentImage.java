package chapter3.ex13;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {

  private final Image in;
  private final List<BaseColorTransformer> pendingOperations = new ArrayList<>();

  public LatentImage(Image in) {
    this.in = in;
  }

  public LatentImage transform(UnaryOperator<Color> f) {
    pendingOperations.add(BaseColorTransformer.from(f));
    return this;
  }

  public LatentImage transform(UniPixelColorTransformer ct) {
    pendingOperations.add(ct.toBaseColorTransformer());
    return this;
  }

  public LatentImage transform(ConvolutionTransformer ct) {
    pendingOperations.add(ct.toBaseColorTransformer());
    return this;
  }

  /*
  前の変換を全てのピクセルに適用してから次の変換を各ピクセルに適用するので、
  変換の際には必ず前段の変換が適用されている。
  
  最適化案: (ただし、ex15で試してみたところ実装が非常に難しかったのでex15では別の方法で実装した)
  各変換を1行ずつ並行に適用する。
  各変換は前段の変換が次の行まで適用された行まで変換し、前段の変換が未適用の場合は待つ。
  
  □□□□ 　　　　　　　　↓　変換方向
  □□□□
  ■■■■ ← 変換2適用可
  ■■■■ ← 変換1適用済
  
  例えば上のように変換1が4行目まで適用済みだった場合、変換2は3行目までは並行して適用可能である。
  */
  public Image toImage() {
    int width = (int) in.getWidth();
    int height = (int) in.getHeight();
    WritableImage out = null;
    for (int i = 0; i < pendingOperations.size(); i++) {
      BaseColorTransformer ct = pendingOperations.get(i);
      PixelReader reader = (i == 0) ? in.getPixelReader() : out.getPixelReader();
      out = new WritableImage(width, height);
      PixelWriter pixelWriter = out.getPixelWriter();
      
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          Color c = ct.apply(x, y, reader);
          pixelWriter.setColor(x, y, c);
        }
      }
    }

    return out;
  }
}
