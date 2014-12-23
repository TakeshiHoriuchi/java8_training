package chapter5.ex12;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppointmentNotifierTest {

  @Test
  public void test_ローカルで1時間後になるUTCの時刻を登録した場合_予定が1回通知される() {
    AppointmentNotifier notifier = new AppointmentNotifier();
    List<Appointment> notified = new ArrayList<>();
    notifier.addObserver(app -> notified.add(app));
    
    ZonedDateTime expectedTime = ZonedDateTime.now(ZoneId.of("UTC")).plusHours(1).plusSeconds(10);
    String expectedDesc = "test";
            
    notifier.addAppointments(new Appointment(expectedTime, expectedDesc));
    
    notifier.start();
    try { Thread.sleep(15000); } catch (InterruptedException ex) { Logger.getLogger(AppointmentNotifierTest.class.getName()).log(Level.SEVERE, null, ex); }
    notifier.stop();
    
    assertEquals(1, notified.size());
    assertEquals(expectedTime, notified.get(0).getTime());
    assertEquals(expectedDesc, notified.get(0).getDescription());
  }
}