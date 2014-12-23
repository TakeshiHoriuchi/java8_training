package chapter5.ex12;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


public class AppointmentNotifier {

  private final List<Appointment> appointments;
  private final List<Consumer<Appointment>> observers;
  private ScheduledFuture<?> future;
  private final ZoneId localZone;
  
  public AppointmentNotifier() {
    appointments = new ArrayList<>();
    observers = new ArrayList<>();
    localZone = ZoneId.systemDefault();
  }
  
  public void start() {
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    future = scheduler.scheduleWithFixedDelay(
            () -> {
              appointments.stream().
                      filter(app -> !app.isNotified()).
                      filter(app -> app.getTime().withZoneSameInstant(localZone).minusHours(1).isBefore(ZonedDateTime.now())). // ローカル時刻で1時間前に約束があることを
                      forEach(app -> { // ユーザに通知する
                        observers.stream().forEach(ob -> ob.accept(app));
                        app.setIsNotified(true);
                      });
            }, 
            0, 3, TimeUnit.SECONDS);
  }
  
  public void stop() {
    if (future != null) future.cancel(true);
  }
  
  public void addObserver(Consumer<Appointment> observer) {
    observers.add(observer);
  }
  
  public void addAppointments(Appointment... apps) {
    for (Appointment app: apps) 
      appointments.add(new Appointment(app));
  }
}
