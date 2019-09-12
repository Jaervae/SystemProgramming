
import java.util.Collections;

class Tamagotchi{

	private int number;
	private volatile int hungryState;
	private String status;
	
	public Tamagotchi(int mNumber, int mHungryState, String mStatus){
	  
		number = mNumber;
		hungryState = mHungryState;
		status = mStatus;
		 
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