public class Status extends Thread{
	
	public interface ThreadReporterInterface {
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
		callBackInterface.progMade(objStatus);
		try {
			while (true) {	
				sleep(500);
				int t = objStatus.getStatus() + 10;
				objStatus.setStatus(t);
				callBackInterface.progMade(objStatus);
				if ( t == 100 ){
					break;
				}				
			}
		} catch(Exception e) {
		  e.printStackTrace();
		  callBackInterface.interupted();
		}
	}
	
}