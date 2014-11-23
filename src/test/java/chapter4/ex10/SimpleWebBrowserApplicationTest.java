package chapter4.ex10;

import java.util.concurrent.TimeUnit;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.web.WebView;
import org.junit.Test;
import org.junit.BeforeClass;
import static org.loadui.testfx.Assertions.verifyThat;
import org.loadui.testfx.GuiTest;
import spark.Spark;

/*
TODO:
buttonAccess を押してから実際にWebページが表示されるまでは、WebEngine#loadで非同期に実行される。
そのため、表示までsleepすることで対処している。

本来は表示完了を表すフラグを認識して待機するべき。
ただし、このフラグを提供するWebEngine#getLoadWorker#isRunning は、
Java FX アプリケーションと同一スレッドで呼び出さない限りRuntimeExceptionが発生する。
つまり、テストコード中にWebEngine#getLoadWorker#isRunningを記述したとしても呼び出しに失敗する。
例えば、テスト用にアプリケーションコード中のvisibleでない要素に、
WebEngine#loadの進行状態を表すノードを追加するなどの対策が必要である。
*/
public class SimpleWebBrowserApplicationTest extends GuiTest {
  @BeforeClass
  public static void setUpStubWebServer() {
    Spark.get("/", (req, res) -> "Fuga");
    Spark.get("/foo", (req, res) -> "foo");
  }
  
  @Test
  public void test_get_web_page() {
    click("#textURL").type("http://localhost:4567/").click("#buttonAccess");
    sleep(500, TimeUnit.MILLISECONDS);
    
    verifyThat("#webView", (WebView view) -> {
      String text = view.getEngine().getDocument().getDocumentElement().getTextContent();
      return text.contains("Fuga");
    });
  }
  
  @Test
  public void test_back_button() {
    click("#textURL").type("http://localhost:4567/").click("#buttonAccess");
    sleep(500, TimeUnit.MILLISECONDS);
    
    verifyThat("#webView", (WebView view) -> {
      String text = view.getEngine().getDocument().getDocumentElement().getTextContent();
      return text.contains("Fuga");
    });
    
    click("#textURL").push(KeyCode.CONTROL, KeyCode.A).push(KeyCode.BACK_SPACE).
            type("http://localhost:4567/foo").click("#buttonAccess");
    sleep(500, TimeUnit.MILLISECONDS);
    
    verifyThat("#webView", (WebView view) -> {
      String text = view.getEngine().getDocument().getDocumentElement().getTextContent();
      return text.contains("foo");
    });
    
    click("#buttonBack");
    sleep(500, TimeUnit.MILLISECONDS);
    
    verifyThat("#webView", (WebView view) -> {
      String text = view.getEngine().getDocument().getDocumentElement().getTextContent();
      return text.contains("Fuga");
    });
  }

  @Override
  protected Parent getRootNode() {
    return SimpleWebBrowserApplication.getRoot();
  }
}