package chapter6.ex01;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaxLengthStringHolderTest {

  @Test
  public void testSetIfLarge_複数のスレッドから並行に値を設定した場合でも最大長の文字列を保持していること() {
    
    // テスト文字列生成
    List<String> words = Stream.generate(() -> {
      return RandomStringUtils.randomAscii(RandomUtils.nextInt(1, 10000));
    }).limit(10000).collect(Collectors.toList());
    
    // 並行に値を設定する
    MaxLengthStringHolder holder = new MaxLengthStringHolder();
    words.parallelStream().forEach(str -> holder.setIfLarge(str));
    
    // 比較
    assertEquals(
            holder.get().length(), 
            words.stream().max((String s1, String s2) -> s1.length() - s2.length()).get().length()
    );
  }

}