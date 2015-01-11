package chapter6.ex10;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.fluent.Request;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class WebUtil {

  public static CompletableFuture<List<URL>> getAllLinks(InputStream in) {

    return CompletableFuture.
            supplyAsync(() -> {
              try {
                return IOUtils.toString(in);
              } catch (IOException ex) {
                throw new RuntimeException(ex);
              }
            }).
            thenApplyAsync(unchecked(str -> Request.Get(str).execute().returnContent())).
            thenApplyAsync(unchecked(content -> {
              SAXReader reader = new SAXReader();
              Document document = reader.read(content.asStream());
              List<Attribute> list = document.getRootElement().selectNodes("//a/@href");
              return list.stream().map(unchecked(attr -> new URL(attr.getValue()))).
                      collect(Collectors.toList());
            }));
  }

  private static <T, U> Function<T, U> unchecked(ThrowableFunction<T, U> t) {
    return (T x) -> {
      try {
        return t.apply(x);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

  @FunctionalInterface
  private static interface ThrowableFunction<T, U> {

    U apply(T t) throws Exception;
  }
}
