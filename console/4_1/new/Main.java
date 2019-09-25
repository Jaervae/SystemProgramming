import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class Main implements Status.ThreadReporterInterface{
	
	private List<Status> statuses = new ArrayList<>();
    private List<StatusUpdatedListener> listeners = new ArrayList<>();
	private int nmbrOfProgressBars = 1 ;
	
    public static void main (String[] args) {
        // Create the zoo to store animals
        Main main = new Main();
		main.startThread();
    }
	
	public void startThread(){
		
		for (int i = 0 ; i < nmbrOfProgressBars ; i++){	
			Status status = new Status(this);
			status.start();	
		}	
	}
	
	public void addProggressStatus (Status status) {
        // Add the animal to the list of animals
        this.statuses.add(status);
        // Notify the list of registered listeners
        this.notifyStatusListeners(status);
    }
    public void registerStatusListener (StatusUpdatedListener listener) {
        // Add the listener to the list of registered listeners
        this.listeners.add(listener);
    }

    protected void notifyStatusListeners (Status status) {
        // Notify each of the listeners in the list of registered listeners
        this.listeners.forEach(listener -> listener.onStatusUpdated(status));
    }
	
	
	public void setListeners(Status status){
		this.registerStatusListener(new PrintUpdateStatus());
        this.addProggressStatus(status);	
	}
	
	public void interupted(){
		System.out.println("thread interrupted");
	}
	
	public void progMade(Status status){
		this.listeners.forEach(listener -> listener.onStatusUpdated(status));
	}
	
}