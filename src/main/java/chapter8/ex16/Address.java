package chapter8.ex16;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {
  private final String city;
  private final String state;
  private final String zipCode;

  public Address(String city, String state, String zipCode) {
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
  }
  
  public static Address parse(String str) {
    Matcher matcher = Pattern.compile("(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2})\\s+(?<zipcode>[0-9]{5}|([0-9]{5}\\-?[0-9]{4}))").matcher(str);
    if (matcher.matches()) {
      return new Address(
              matcher.group("city"),
              matcher.group("state"),
              matcher.group("zipcode")
      );
    }
    else
      return null;
  }
  
  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * @return the zipCode
   */
  public String getZipCode() {
    return zipCode;
  } 
}
