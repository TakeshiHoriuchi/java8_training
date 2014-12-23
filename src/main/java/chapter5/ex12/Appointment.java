package chapter5.ex12;

import java.time.ZonedDateTime;

public class Appointment {

  private ZonedDateTime time;
  private String description;
  private boolean isNotified;

  public Appointment(ZonedDateTime time, String description) {
    this.time = time;
    this.description = description;
    this.isNotified = false;
  }
  
  public Appointment(Appointment app) {
    this.time = app.time;
    this.description = app.description;
    this.isNotified = app.isNotified;
  }

  public ZonedDateTime getTime() {
    return time;
  }

  public String getDescription() {
    return description;
  }
  
  public boolean isNotified() {
    return isNotified;
  }

  public void setIsNotified(boolean isNotified) {
    this.isNotified = isNotified;
  }
}
