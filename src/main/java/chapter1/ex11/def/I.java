package chapter1.ex11.def;

public interface I {
  default void f() {
    System.out.println("Iのデフォルトメソッド");
  }
}
