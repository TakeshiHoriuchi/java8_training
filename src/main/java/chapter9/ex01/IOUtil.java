package chapter9.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

// TODO: jMockitで頑張ってテストする
public class IOUtil {
  public static void print() {
    Scanner in = null;
    PrintWriter out = null;
    try {
      in = new Scanner(Paths.get("/src"));
      out = new PrintWriter("/out");
      while(in.hasNext()) out.println(in.next().toLowerCase());
    } catch (IOException e) {
    } finally {
      try {
        if (in != null) in.close();
      } catch (RuntimeException e) {
      } finally {
        if (out != null) out.close();
      }
    }
  }
}