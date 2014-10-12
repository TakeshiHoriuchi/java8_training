package chapter1.ex8;

/*
for-each 文内のラムダ式では評価されたスコープ内の name の値がキャプチャされる。
つまり、以下のプログラムは Peter, Mary, Paul の順に出力する。
for (int...) の文を使用すると、ラムダ式から参照される変数は final または
事実上の final (コンパイラが判断する?) である必要があるとしてエラー。
*/

import java.util.ArrayList;
import java.util.List;

public class CaptureTest {
  public static void main(String[] args) {
    String[] names = { "Peter", "Paul", "Mary" };
    List<Runnable> runners = new ArrayList<>();
    for (String name: names) 
      runners.add(() -> System.out.println(name));
    runners.forEach(r -> { new Thread(r).start(); });
    
//    for (int i=0; i < names.length; i++)
//      runners.add(() -> System.out.println(names[i]));
  }
}
