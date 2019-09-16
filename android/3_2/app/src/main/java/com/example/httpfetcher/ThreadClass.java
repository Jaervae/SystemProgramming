package com.example.httpfetcher;

import android.util.Log;

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
		Log.d("Tester", http);
		try {
			String content = null;
			URLConnection connection = null;
			Log.d("Tester", "1");
			try {
				Log.d("Tester", "2");
			  connection =  new URL(http).openConnection();
				Log.d("Tester", "3");
			  Scanner scanner = new Scanner(connection.getInputStream());
				Log.d("Tester", "4");
			  scanner.useDelimiter("\\Z");
				Log.d("Tester", "5");
			  content = scanner.next();
				Log.d("Tester", "6");
			  scanner.close();
			  Log.d("Tester", "closing scanner");
			}catch ( Exception ex ) {
				ex.printStackTrace();
				Log.d("Tester", "ERROR 1:" + ex.toString());
			}
			callBackInterface.printHTTP(content);
		} catch(Exception e) {
			e.printStackTrace();
			Log.d("Tester", "ERROR 2:" + e.toString());
		  callBackInterface.interupted();
		}
	}
}