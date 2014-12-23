package chapter5.ex12;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class AppointmentNotifier {

  private List<Consumer<Appointment>> observers;
  private AppointmentStore store;
  
  // 通知スレッドを開始する
  public void start() {
    
  }
  
  public void addAppointmentObserver(Consumer<Appointment> observer) {
    observers.add(observer);
  }
  
  public void addAppointment(Appointment appointment) {
    store.add(appointment);
  }
  
  private static class AppointmentStoreImpl implements AppointmentStore {
    
    List<Appointment> appointments;
    
    public AppointmentStoreImpl() {
      appointments = new ArrayList<>();
    }
    
    @Override
    public void add(Appointment appointment) {
      appointments.add(new Appointment(appointment));
    }

    @Override
    public Collection<Appointment> search(Predicate<Appointment> pred) {
      return appointments.stream().filter(pred).
              map(app -> new Appointment(app)).collect(Collectors.toList());
    }
  }
}
