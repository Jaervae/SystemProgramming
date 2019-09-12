public class Animal extends Thread{
	
    private String status;
	private int number;
	private int hungryState;
	

	
	//Constructor
    public Animal (String status, int number, int hungryState) {
        this.status = status;
		this.number = number;
		this.hungryState = hungryState;
		
		
		//Animal animalThread = new Animal(number);
		//animalThread.start();
		
    }	
	
	//Number Getter & Setter
    public int getNumber () {
        return this.number;
    }
    public void setNumber (int number) {
        this.number = number;
    }	
	
	//HungryState Getter & Setter
    public int getHungryState () {
        return this.hungryState;
    }
    public void setHungryState (int hungryState) {
        this.hungryState = hungryState;
    }
	
	//Status Getter & Setter
    public String getStatus () {
        return this.status;
    }
    public void setStatus (String status) {
        this.status = status;
    }
	
}