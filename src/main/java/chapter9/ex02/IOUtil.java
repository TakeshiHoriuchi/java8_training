package chapter9.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

// TODO: jMockitで頑張ってテストする
public class IOUtil {
  public static void print() throws Throwable {
    Scanner in = null;
    PrintWriter out = null;
    Throwable prevException = null;
    try {
      in = new Scanner(Paths.get("/src"));
      out = new PrintWriter("/out");
      while(in.hasNext()) out.println(in.next().toLowerCase());
    } catch (IOException ioe) {
      prevException = ioe;
    } finally {
      try {
        if (in != null) in.close();
      } catch (RuntimeException e) {
        throwSurpressException(prevException, e);
      } finally {
        try {
          if (out != null) out.close();
        } catch (RuntimeException e) {
          throwSurpressException(prevException, e);
        }
      }
    }
  }
  
  private static void throwSurpressException(Throwable prev, Throwable current) throws Throwable {
    if (prev != null) {
      prev.addSuppressed(current);
      throw prev;
    }
    else
      throw current;
  }
}