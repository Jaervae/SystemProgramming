
import java.time.*;
import java.time.temporal.ChronoUnit;

public class ThreadClass extends Thread {


    //Initially setting the flag as true
     
    private volatile boolean flag = true;
     
    //This method will set flag as false
     
    public void stopRunning()
    {
        flag = false;
    }
	
	    public void startRunning()
    {
        flag = true;
    }

	public void run() {
		
		try {
			while (flag) {
				
			
					System.out.println("Tiisu, We don't wanna to hear more!!");
					sleep(5000);
		
			
		  }
		} catch(Exception e) {
		  e.printStackTrace();
		}
	}
}
