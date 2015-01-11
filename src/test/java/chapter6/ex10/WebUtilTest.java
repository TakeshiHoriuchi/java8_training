package chapter6.ex10;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import spark.Spark;

public class WebUtilTest {

  @Test
  public void testGetAllLinks() throws IOException, InterruptedException, ExecutionException {
    // テスト用Webサーバ起動
    Spark.get("/", (req, res) -> {
      try {
        File file = new File(WebUtilTest.class.getResource("test.html").toURI());
        return FileUtils.readFileToString(file, "UTF-8");
      } catch (IOException | URISyntaxException ex) {
        throw new RuntimeException(ex);
      }
    });
    
    InputStream is = IOUtils.toInputStream("http://localhost:4567/", "UTF-8");
    CompletableFuture<List<URL>> cf = WebUtil.getAllLinks(is);
    
    ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
    
    List<String> actualList = cf.get().stream().
            map(url -> url.toString()).collect(Collectors.toList());
    Stream.of("http://example1.com/", "http://example2.com/", "http://example3.com/").forEach(url ->{
      assertTrue(actualList.contains(url));
    });
  }
}
