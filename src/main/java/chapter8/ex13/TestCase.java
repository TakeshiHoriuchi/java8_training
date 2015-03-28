package chapter8.ex13;

import java.lang.annotation.Repeatable;

@Repeatable(TestCases.class)
@interface TestCase {
  String params();
  String expected();
}

@interface TestCases {
  TestCase[] value();
}
