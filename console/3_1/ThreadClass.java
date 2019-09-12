import java.util.Scanner;
import java.net.*;
import java.io.*;

class ThreadClass extends Thread{
	
	
	public interface ThreadClassReporterInterFace {	
		//Methods here...
		void interupted();
		String getHTTP();
		void printHTTP(String mHttp);
	}
	
	public ThreadClass(ThreadClassReporterInterFace cb){
		callBackInterface = cb;
	}
	
	ThreadClassReporterInterFace callBackInterface = null;
	
	public void run() {
		
		String http = callBackInterface.getHTTP();
		try {
			String content = null;
			URLConnection connection = null;
			try {
			  connection =  new URL(http).openConnection();
			  Scanner scanner = new Scanner(connection.getInputStream());
			  scanner.useDelimiter("\\Z");
			  content = scanner.next();
			  scanner.close();
			}catch ( Exception ex ) {
				ex.printStackTrace();
			}
			callBackInterface.printHTTP(content);
		} catch(Exception e) {
			e.printStackTrace();
		  callBackInterface.interupted();
		}
	}
}