package chapter8.ex12;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TestCases.class)
@interface TestCase {
  String params();
  String expected();
}

@Retention(RetentionPolicy.RUNTIME)
@interface TestCases {
  TestCase[] value();
}
