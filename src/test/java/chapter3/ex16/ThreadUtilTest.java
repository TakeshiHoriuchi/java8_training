package chapter3.ex16;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

// Web API に (複数並列に) アクセスして得たJSONからHTMLを生成する場合を考える。
// 正常にWeb APIにアクセス成功した場合はJSONからHTMLを生成するが、
// コネクションエラーなどで失敗した場合はエラーメッセージを表示するHTMLを生成する。
// 三つ目の引数でThreadの待ちのための処理を実行する
public class ThreadUtilTest {

  String resultHTML;
  
  @Before
  public void setUp() { resultHTML = null; }
  
  @Test
  public void testDoInOrderAsyncRegular() throws InterruptedException {
    List<Thread> list = new LinkedList<>();
    ThreadUtil.doInOrderAsync(
            () -> new StubResponseObj("testName", "testValue"), 
            (new StubBifunction())::accept,
            t -> list.add(t));
    for (Thread t: list) t.join();
    
    assertEquals("<div><span>testName</span><span>testValue</span></div>", resultHTML);
  }
  
  @Test
  public void testDoInOrderAsyncEregular() throws InterruptedException {
    List<Thread> list = new LinkedList<>();
    ThreadUtil.doInOrderAsync(
            () -> { throw new StubConnectionException("コネクションエラー"); }, 
            (new StubBifunction())::accept,
            t -> list.add(t));
    for (Thread t: list) t.join();
    
    assertEquals("<div>コネクションエラー</div>", resultHTML);
  }
  
  class StubBifunction implements BiConsumer<StubResponseObj, Throwable> {
    @Override
    public void accept(StubResponseObj res, Throwable u) {
      if (u != null)
        resultHTML = "<div>" + u.getMessage()  + "</div>";
      else
        resultHTML = "<div><span>" + res.name + "</span><span>" + res.value + "</span></div>";
    }
  }
  
  class StubResponseObj {
    public String name;
    public String value;
    
    public StubResponseObj(String name, String value) {
      this.name = name;
      this.value = value;
    }
  }
  
  class StubConnectionException extends RuntimeException {
    public StubConnectionException(String message) {
      super(message);
    }
  }
}