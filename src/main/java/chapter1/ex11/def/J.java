package chapter1.ex11.def;

public interface J {
  default void f() {
    System.out.println("Jのデフォルトメソッド");
  }
}
