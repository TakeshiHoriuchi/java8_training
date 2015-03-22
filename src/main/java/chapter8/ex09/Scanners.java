package chapter8.ex09;

import java.util.Iterator;
import java.util.PrimitiveIterator;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Scanners {

  public static Stream<String> wordsStream(Scanner scanner) {
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED | Spliterator.NONNULL), false);
  }
  
  public static Stream<String> linesStream(Scanner scanner) {
    Iterator<String> iter = new Iterator<String>() {
      @Override public boolean hasNext() { return scanner.hasNextLine(); }
      @Override public String next() { return scanner.nextLine(); }
    };
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
  }
  
  public static IntStream intStream(Scanner scanner) {
    PrimitiveIterator.OfInt iter = new PrimitiveIterator.OfInt() {
      @Override public int nextInt() { return scanner.nextInt(); }
      @Override public boolean hasNext() { return scanner.hasNextInt(); }
    };
    return StreamSupport.intStream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED), false);
  }
  
  public static DoubleStream doubleStream(Scanner scanner) {
    PrimitiveIterator.OfDouble iter = new PrimitiveIterator.OfDouble() {
      @Override public boolean hasNext() { return scanner.hasNextDouble(); }
      @Override public double nextDouble() { return scanner.nextDouble(); }
    };
    return StreamSupport.doubleStream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED), false);
  }
}