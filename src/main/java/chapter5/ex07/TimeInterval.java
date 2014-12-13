package chapter5.ex07;

import java.time.LocalDateTime;


public class TimeInterval {
  private final LocalDateTime start;
  private final LocalDateTime end;
  
  private TimeInterval(LocalDateTime startDateTimeInclusive, LocalDateTime endDateTimeExclusive) {
    this.start = startDateTimeInclusive;
    this.end = endDateTimeExclusive;
  }
  
  public static TimeInterval between(LocalDateTime startDateTimeInclusive, LocalDateTime endDateTimeExclusive) {
    if (!endDateTimeExclusive.isAfter(startDateTimeInclusive)) 
      throw new IllegalArgumentException("startDateTimeInclusive must not be after endDateTimeExclusive");

    return new TimeInterval(startDateTimeInclusive, endDateTimeExclusive);
  }
  
  public boolean abuts(TimeInterval arg) {
    return !(
            start.isAfter(arg.end) || start.isEqual(arg.end) ||
            end.isBefore(arg.start) || end.isEqual(arg.start)
            );
  }
}
