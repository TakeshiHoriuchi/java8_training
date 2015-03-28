package chapter8.ex11;

// ・Rubyが動作し、かつパスが通っている環境で実行すること
// ・4567番ポートがあいていること

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import org.apache.commons.io.IOUtils;

public class BasicAuthTest {
  public static void main(String[] args) throws MalformedURLException, IOException, URISyntaxException {
    // テストサーバ起動
    URI fileUri = BasicAuthTest.class.getResource("testserver.rb").toURI();
    Path path = Paths.get(fileUri).getParent();
    ProcessBuilder processBuilder = new ProcessBuilder("ruby", "testserver.rb");
    processBuilder.directory(path.toFile());
    Process process = processBuilder.start();
    
    // パスワード保護されたウェブページの内容を取得する
    URL url = new URL("http://localhost:4567/hello.txt");
    URLConnection connection = url.openConnection();
    Base64.Encoder encoder = Base64.getEncoder();
    String realm = encoder.encodeToString("user:pass".getBytes());
    connection.setRequestProperty("Authorization", "Basic " + realm);
    connection.connect();

    InputStream in = connection.getInputStream();
    String result = IOUtils.toString(in);
    System.out.println(result);

    // テストサーバ終了
    process.destroy();
  }
}
