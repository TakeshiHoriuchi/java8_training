package chapter3.ex14;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

public class LatentImage {

  private PipelineStageImage pipelineStage;

  public LatentImage(Image in) {
    pipelineStage = new PipelineStageImage(in, (x, y, reader) -> reader.getColor(x, y));
  }
  
  public LatentImage transform(BaseColorTransformer ct) {
    pipelineStage = new PipelineStageImage(pipelineStage, ct);
    return this;
  }
  
  public LatentImage transform(UnaryOperator<Color> f) {
    pipelineStage = new PipelineStageImage(pipelineStage, BaseColorTransformer.from(f));
    return this;
  }

  // BaseColorTransformer と関数の引数の数が被るのでオーバーロードしない名前にしている
  public LatentImage transformByUniPixelColorTransformer(UniPixelColorTransformer ct) {
    pipelineStage = new PipelineStageImage(pipelineStage, ct.toBaseColorTransformer());
    return this;
  }

  public LatentImage transform(ConvolutionTransformer ct) {
    pipelineStage = new PipelineStageImage(pipelineStage, ct.toBaseColorTransformer());
    return this;
  }

  public Image toImage() { return pipelineStage.toImage(); }
  
  public PixelReader getPixelReader() { return pipelineStage.getPixelReader(); }

  // 本文中 "パイプライン中の個々のレベルで最近読み込まれたピクセルのキャッシュを保持する" の各レベルに該当するクラス。
  // ただし無期限に全てのキャッシュを保持する
  private class PipelineStageImage {

    private final int width;
    private final int height;
    private final WritableImage out;
    private final Image prevImage;
    private final PipelineStageImage prevPImage;
    private final BaseColorTransformer operation;
    private final boolean[][] isCalculated;
    private PipelineStagePixelReader selfReader = null;

    public PipelineStageImage(Image prevImage, BaseColorTransformer operation) {
      this.operation = operation;

      this.width = (int) prevImage.getWidth();
      this.height = (int) prevImage.getHeight();
      this.out = new WritableImage(width, height);
      this.isCalculated = new boolean[width][height]; // 全てfalseで初期化される
      
      this.prevImage = prevImage;
      this.prevPImage = null;
    }

    public PipelineStageImage(PipelineStageImage prevPImage, BaseColorTransformer operation) {
      this.operation = operation;

      this.width = prevPImage.getWidth();
      this.height = prevPImage.getHeight();
      this.out = new WritableImage(width, height);
      this.isCalculated = new boolean[width][height];
      
      this.prevImage = null;
      this.prevPImage = prevPImage;
    }

    // 本文中 "ピクセルがなければリーダーを構築し"、に準拠して遅延初期化
    public PixelReader getPixelReader() {
      if (selfReader == null) selfReader = new PipelineStagePixelReader();
      return selfReader;
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }
    
    // getColorを全ピクセルに対して呼び出し、遅延されていた計算を実行する
    public Image toImage() {
      PixelReader reader = getPixelReader();
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          reader.getColor(x, y);
        }
      }
      return out;
    }

    public class PipelineStagePixelReader implements PixelReader {

      @Override
      public Color getColor(int x, int y) {
        if (x < 0 || width <= x || y < 0 || height <= y)
          throw new IndexOutOfBoundsException("x: " + x + " y: " + y + " is out of the image");

        // 本文中 "リーダーはキャッシュを調べます" に準拠
        if (isCalculated[x][y])
          return out.getPixelReader().getColor(x, y);

        // 本文中 "そのリーダーはピクセルを前段階で求めます" に準拠しピクセルを求める
        PixelReader reader = (prevImage != null) ? prevImage.getPixelReader() : prevPImage.getPixelReader();
        Color c = operation.apply(x, y, reader);
        out.getPixelWriter().setColor(x, y, c);
        isCalculated[x][y] = true;

        return out.getPixelReader().getColor(x, y); // そのまま c を返すとキャッシュされた値と異なる場合がある
      }

      @Override
      public PixelFormat getPixelFormat() {
        throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public int getArgb(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public <T extends Buffer> void getPixels(int x, int y, int w, int h, WritablePixelFormat<T> pixelformat, T buffer, int scanlineStride) {
        throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void getPixels(int x, int y, int w, int h, WritablePixelFormat<ByteBuffer> pixelformat, byte[] buffer, int offset, int scanlineStride) {
        throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void getPixels(int x, int y, int w, int h, WritablePixelFormat<IntBuffer> pixelformat, int[] buffer, int offset, int scanlineStride) {
        throw new UnsupportedOperationException("Not supported yet.");
      }
    }
  }
}
