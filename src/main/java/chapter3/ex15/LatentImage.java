package chapter3.ex15;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
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
    private final Color[][] out;
    private final Image prevImage;
    private final PipelineStageImage prevPImage;
    private final BaseColorTransformer operation;
    private PipelineStagePixelReader selfReader = null;

    public PipelineStageImage(Image prevImage, BaseColorTransformer operation) {
      this.operation = operation;

      this.width = (int) prevImage.getWidth();
      this.height = (int) prevImage.getHeight();
      this.out = new Color[width][height];
      
      this.prevImage = prevImage;
      this.prevPImage = null;
    }

    public PipelineStageImage(PipelineStageImage prevPImage, BaseColorTransformer operation) {
      this.operation = operation;

      this.width = prevPImage.getWidth();
      this.height = prevPImage.getHeight();
      this.out = new Color[width][height];
      
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
    
    // getColorを全ピクセルに対して呼び出し、遅延されていた計算を実行する。
    // 本文に準拠し並列して呼び出す。
    //
    // 複数のピクセルを結合する画像操作を実行する場合、
    // 複数のスレッドで値が必要になるピクセルが存在するがパイプライン中のキャッシングにより、
    // あるピクセルの計算が一方のスレッドで終了していた場合、
    // もう一方のスレッドは前のスレッドが計算してキャッシュした値を使用するので、
    // 複数のスレッドで同じピクセルに対して同じ計算はしない場合が多い。
    // ただし、厳密にキャッシュの確認から計算してキャッシュへ代入するまでをアトミックにしていないので、同じ計算をする場合もある。
    public Image toImage() {
      PixelReader reader = getPixelReader();
      
      int n = Runtime.getRuntime().availableProcessors();
      
      try {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < n; i++) {
          int fromY = i * height / n;
          int toY = (i + 1) * height / n;
          pool.submit(() -> {
            for (int x = 0; x < width; x++)
              for (int y = fromY; y < toY; y++)
                reader.getColor(x, y);
          });
        }
        pool.shutdown();
      
        pool.awaitTermination(1, TimeUnit.HOURS);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      
      return arrayToImage(out);
    }
    
    private Image arrayToImage(Color[][] colors) {
      WritableImage resultImage = new WritableImage(width, height);
      PixelWriter pixelWriter = resultImage.getPixelWriter();
      
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          pixelWriter.setColor(x, y, colors[x][y]);
       }
      }
    
      return resultImage;
    }

    public class PipelineStagePixelReader implements PixelReader {

      @Override
      public Color getColor(int x, int y) {
        if (x < 0 || width <= x || y < 0 || height <= y)
          throw new IndexOutOfBoundsException("x: " + x + " y: " + y + " is out of the image");

        // 本文中 "リーダーはキャッシュを調べます" に準拠
        if (out[x][y] == null) {
          // 本文中 "そのリーダーはピクセルを前段階で求めます" に準拠しピクセルを求める
          PixelReader reader = (prevImage != null) ? prevImage.getPixelReader() : prevPImage.getPixelReader();
          out[x][y] = operation.apply(x, y, reader);
        }

        return out[x][y];
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
