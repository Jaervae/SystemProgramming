import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Task());

        try {
            System.out.println("Started..");
            System.out.println(future.get(10, TimeUnit.SECONDS));
            System.out.println("Finished!");
        } catch (TimeoutException e) {
            future.cancel(true);
            System.out.println("Terminated!");
        }
        executor.shutdownNow();
    }
}

class Task implements Callable<String> {
	private static final int SCALE = 10000;  
    private static final int ARRINIT = 2000; 
    @Override
    public String call() throws Exception { 
		StringBuffer pi = new StringBuffer(); 
		int digits = 1000000;
		int[] arr = new int[digits + 1];  
		int carry = 0;  
		for (int i = 0; i <= digits; ++i)  
			arr[i] = ARRINIT;  

		for (int i = digits; i > 0; i-= 14) { 
			int sum = 0;  
			for (int j = i; j > 0; --j) {			
				sum = sum * j + SCALE * arr[j];  
				arr[j] = sum % (j * 2 - 1);  
				sum /= j * 2 - 1; 			
			}  
			pi.append(String.format("%04d", carry + sum / SCALE));  
			carry = sum % SCALE;
			if(Thread.interrupted()){
				System.out.println(pi.toString() + ". Time outed");
				break;
			}
		}  
        return pi.toString();
    }
	
}