package chapter5.ex12;

import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicInteger;


class Appointment {
  private static AtomicInteger idBase = new AtomicInteger(0);
  
  private ZonedDateTime zdt;
  private String description;
  private boolean isNotified;
  private final int id;
  
  public Appointment(ZonedDateTime time) {
    zdt = time;
  }
  
  public Appointment(Appointment app) {
    this.zdt = app.zdt;
    this.isNotified = app.isNotified;
  }
  
  @Override
  public boolean equals(Object obj) {
    Appointment app = (Appointment)obj;
    return zdt.equals(app.zdt);
  }

  @Override
  public int hashCode() {
    return zdt.hashCode();
  }
}
