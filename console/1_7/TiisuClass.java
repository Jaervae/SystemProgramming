
import java.time.*;
import java.time.temporal.ChronoUnit;

public class TiisuClass extends Thread {

  public void run() {
    try {
        while (true) {
			
			System.out.println("Tiisu, We don't wanna to hear more!!");
			sleep(5000);
		  
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
