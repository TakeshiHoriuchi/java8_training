package chapter5.ex02;

import java.time.LocalDate;

/*
実行結果：

2000-02-29に1年を加算:    2001-02-28
2000-02-29に4年を加算:    2004-02-29
2000-02-29に1年を4回加算: 2004-02-28

考察：
中間結果として閏年でない年を求めると28日に変換されると思われる
*/
public class LocalDateTest {

  public static void main(String[] args) {
    LocalDate date2000_02_29 = LocalDate.of(2000, 2, 29);    
    
    System.out.println("2000-02-29に1年を加算:    " + date2000_02_29.plusYears(1));
    System.out.println("2000-02-29に4年を加算:    " + date2000_02_29.plusYears(4));
    System.out.println("2000-02-29に1年を4回加算: " + date2000_02_29.plusYears(1).plusYears(1).plusYears(1).plusYears(1));
  }
}
