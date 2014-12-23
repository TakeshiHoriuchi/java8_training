package chapter5.ex12;

import java.util.Collection;
import java.util.function.Predicate;


public interface AppointmentStore {
  void add(Appointment appointment);
  Collection<Appointment> search(Predicate<Appointment> pred);
}
