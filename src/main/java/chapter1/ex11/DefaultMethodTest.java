package chapter1.ex11;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

class Iの抽象メソッドとJの抽象メソッドを継承 implements chapter1.ex11.abs.I, chapter1.ex11.abs.J {
  @Override
  public void f() {
    System.out.println("メソッドを実装する必要がある");
  }
}

class Iの抽象メソッドとJのデフォルトメソッドを継承 implements chapter1.ex11.abs.I, chapter1.ex11.def.J {
  @Override
  public void f() {
//    super.f();
    System.out.println("メソッドを実装する必要がある。super.f()を呼び出そうとしても見つからない");
  }
}

class Iの抽象メソッドとJのstaticメソッドを継承 implements chapter1.ex11.abs.I, chapter1.ex11.sta.J {
  @Override
  public void f() {
    System.out.println("メソッドを実装する必要がある");
  }
}

//class IのデフォルトメソッドとJのデフォルトメソッドを継承 implements chapter1.ex11.def.I, chapter1.ex11.def.J {
//}

class IのデフォルトメソッドとJのstaticメソッドを継承 implements chapter1.ex11.def.I, chapter1.ex11.sta.J {
}

class IのstaticメソッドとJのstaticメソッドを継承 implements chapter1.ex11.sta.I, chapter1.ex11.sta.J {
}

class Iの抽象メソッドとSのメソッドを継承 extends S implements chapter1.ex11.abs.I {
}

class IのデフォルトメソッドとSのメソッドを継承 extends S implements chapter1.ex11.def.I {
}

class IのstaticメソッドとSのメソッドを継承 extends S implements chapter1.ex11.sta.I {
}

public class DefaultMethodTest {
  public static void main(String[] args) {
    String packageName = DefaultMethodTest.class.getPackage().getName();
    Stream.of("抽象メソッド", "デフォルトメソッド", "staticメソッド").forEach((i) -> {
      Stream.of("Jの抽象メソッド", "Jのデフォルトメソッド", "Jのstaticメソッド", "Sのメソッド").forEach((j) -> {
        try {
          Class<?> klass = Class.forName(String.format("%s.Iの%sと%sを継承", packageName, i, j));
          System.out.println("\n" + klass.getName().substring(packageName.length()+1) + "したクラスのf(): ");
          try {
            System.out.print("  インスタンスメソッド: ");
            klass.getMethod("f", null).invoke(klass.newInstance(), null);
          } catch (NoSuchMethodException e) { System.out.println("呼び出しエラー"); }
          try {
            System.out.print("  staticメソッド: ");
            klass.getMethod("f", null).invoke(null, null);
          } catch (NullPointerException | NoSuchMethodException e) { System.out.println("呼び出しエラー"); }
        } catch (ClassNotFoundException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//          System.err.println(ex);
        }
        
        if (i.equals("デフォルトメソッド") && j.equals("Jのデフォルトメソッド"))
          System.out.println("\nIのデフォルトメソッドとJのデフォルトメソッドを継承しようとするとコンパイルエラー");
      });
    });
  }
}
