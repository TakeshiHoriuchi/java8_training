package chapter1.ex6;

public interface RunnableEx {
  /*
  仮引数の型 RunnableEx を Callable<Void> にすると実引数にラムダ式を代入したとき、
  void を Void に変換できないエラーが発生する。
  */
  public static Runnable uncheck(RunnableEx runner) {
    return (() -> { try { runner.run(); } catch (Throwable ex) {} });
  }

  public void run() throws Throwable;
}
