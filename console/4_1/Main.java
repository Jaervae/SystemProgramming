class Main{
		
    public static void main(String[] args) {
		/*
		Status status = new Status();
		status.registerStatusListener(new PrintStatusListener());
		status.addStatus(new Status(0));
		*/
		Main main = new  Main();
		main.startThread();
	}
	
	public void startThread(){
		Status obj = new Status();
		obj.start();
	}
}