import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Status extends Thread{
	
	private int status;
	private List<Status> statuses = new ArrayList<>();
    private List<StatusListener> listeners = new ArrayList<>();
	
	public void addStatus ( Status status ){
		this.statuses.add(status);
		this.notifyStatusListeners(status);

	}

	public void registerStatusListener(StatusListener listener){
		this.listeners.add(listener);
	}
	
	
	protected void notifyStatusListeners(Status status){
		this.listeners.forEach( listener -> listener.onStatusUpdated(status));
	}
	
	public void run(){
		Status stats = new Status();
		stats.setStatus(0);
		stats.registerStatusListener(new PrintStatusListener());
		stats.addStatus(new Status(0));
		while(true){
			try {
				//System.out.print("1");
				sleep(500);
				//System.out.print("2");

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//Constructors
	public Status(){}
	public Status(int status){
			this.status = status;
	}
	
	//GETter SETter
	public int getStatus(){
		return this.status;
	}	
	public void setStatus(int status){
		this.status = status;
	}
	
}