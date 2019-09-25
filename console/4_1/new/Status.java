public class Status extends Thread{
	
	public interface ThreadReporterInterface {
		void setListeners(Status status);
		void interupted();
		void progMade(Status status);
	}
	
	public Status(ThreadReporterInterface cb){
		callBackInterface = cb;
	}
	
	ThreadReporterInterface callBackInterface = null;
	
	private int status;	
	
	public Status ( int status ){
		this.status = status;
	}
	
    public int getStatus () {
        return this.status;
    }
    public void setStatus (int status) {
        this.status = status;
    }
	
	public void run(){
		Status objStatus = new Status(0);
		callBackInterface.setListeners(objStatus);
		try {
			while (true) {	
				sleep(3000);
				int t = objStatus.getStatus() + 10;
				objStatus.setStatus(t);
				callBackInterface.progMade(objStatus);
				if ( t == 100 ){
					System.out.println("On_Completed");
					break;
				}				
			}
		} catch(Exception e) {
		  e.printStackTrace();
		  callBackInterface.interupted();
		}
	}
	
}