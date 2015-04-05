package chapter8.ex16;

import com.github.javafaker.Faker;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddressTest {

  @Test
  public void testParse() {
    Faker faker = new Faker();
    for (int i = 0; i < 1000; i++) {
      String city = faker.address().cityPrefix() + " " + faker.address().citySuffix();
      String state = faker.address().stateAbbr();
      String zipCode = faker.address().zipCode();
      String input = city + ", " + state + " " + zipCode;

      Address actual = Address.parse(input);
      assertAddress(actual, city, state, zipCode);
    }
  }

  void assertAddress(Address actual, String city, String state, String zipCode) {
    assertEquals(actual.getCity(), city);
    assertEquals(actual.getState(), state);
    assertEquals(actual.getZipCode(), zipCode);
  }
}
