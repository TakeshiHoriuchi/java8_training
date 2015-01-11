package chapter6.ex11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CompletableFutureUtilTest {

  @Test
  public void testRepeat() throws IOException, InterruptedException, ExecutionException {
    List<String> results = new ArrayList<>();
    BufferedReader reader = new BufferedReader(
            new StringReader(
                    "user\nwrongpassword1\n"
                    + "user\nwrongpassword2\n"
                    + "user\nsecret\n"
            )
    );
    CompletableFuture<PasswordAuthentication> cf = CompletableFutureUtil.repeat(
            () -> {
              try {
                return new PasswordAuthentication(reader.readLine(), reader.readLine().toCharArray());
              } catch (IOException ex) {
                throw new RuntimeException(ex);
              }
            },
            (auth) -> {
              try {
                Thread.sleep(1000);
              } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
              }
              results.add(String.valueOf(auth.getPassword()));
              return Arrays.equals(auth.getPassword(), "secret".toCharArray());
            }
    );
    assertEquals(String.valueOf(cf.get().getPassword()), "secret");
    assertArrayEquals(
            results.toArray(new String[0]), 
            new String[] { "wrongpassword1", "wrongpassword2", "secret" }
    );
  }

}
