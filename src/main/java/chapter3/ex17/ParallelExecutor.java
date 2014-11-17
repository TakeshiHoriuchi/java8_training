package chapter3.ex17;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParallelExecutor {
  private Thread t1;
  private Thread t2;
  
  public void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
    t1 = new Thread(() -> {
      try {
        first.run();
      } catch (Throwable ex) { 
        handler.accept(ex);
      }
    });
    t2 = new Thread(() -> {
      try {
        second.run();
      } catch (Throwable ex) { 
        handler.accept(ex);
      }
    });
    
    t1.start();
    t2.start();
  }
  
  public void joinAll() {
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException ex) {
      Logger.getLogger(ParallelExecutor.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
