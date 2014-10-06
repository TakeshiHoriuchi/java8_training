// すべて同じスレッドで実行される

package horiuchi.java8training.chapter1.ex1;

import java.util.Arrays;
import java.util.Random;

public class ArraysThreadCheck {
    public static void main(String[] args) {
        Random r = new Random();
        Integer[] ints = r.ints(10000).boxed().toArray(Integer[]::new);
        
        System.out.println("メインスレッドのID: " + Thread.currentThread().getId());
        Arrays.sort(
            ints,
            (first, second) -> {
                System.out.println("コンパレータの実行スレッドのID: " + Thread.currentThread().getId());
                return Integer.compare(first, second);
            }
        );
    }
}
