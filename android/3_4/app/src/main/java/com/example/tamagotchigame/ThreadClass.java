package com.example.tamagotchigame;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class ThreadClass extends Thread {

	
	public interface TamagotchiThreadReporterInterface {
		void dataChanged(int id, int hungry);
		int GetHungryStatus(int id);
		int getOwnTamagotchi();
		void interupted();
		boolean checkIfNeedToEat(int id);
	}
	
	public ThreadClass(TamagotchiThreadReporterInterface cb){
		callBackInterface = cb;
	}
	
	TamagotchiThreadReporterInterface callBackInterface = null;
	private volatile boolean flag = true;
	private int id;
	
	private int number;
	private volatile int hungryState;
	private String status;

     
    public void stopRunning(){
        flag = false;
    }
	
	public void startRunning(){
		
        flag = true;
    }
	
	public ThreadClass(int mNumber, int mHungryState, String mStatus){
	  
		number = mNumber;
		hungryState = mHungryState;
		status = mStatus;
		 
	}

	
	public void run() {
	
		
		int id = callBackInterface.getOwnTamagotchi();
		
		ThreadClass obj = new ThreadClass(id,10,"Alive");

		try {
			while (flag) {	
			sleep(2000);
			int temp = obj.getHungryState() - 1;
			obj.setHungryState(temp);
			if (temp < 0 || temp > 20 ){
				stopRunning();
				System.out.println("" +temp);
				callBackInterface.dataChanged(id, temp);
				break;
			}
			if(callBackInterface.checkIfNeedToEat(id)){
				temp = obj.getHungryState() + 10;
				obj.setHungryState(temp);
			}
			
				
			callBackInterface.dataChanged(id, temp);
				
		  }
		} catch(Exception e) {
		  e.printStackTrace();
		  callBackInterface.interupted();
		}
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumber() {
		return number;
	}
  
	public void setHungryState(int hungryState) {
		this.hungryState = hungryState;
	}
	public int getHungryState() {
		return hungryState;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
}
