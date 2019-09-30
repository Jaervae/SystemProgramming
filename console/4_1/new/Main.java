import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class Main implements Status.ThreadReporterInterface{
	
	private List<Status> statuses = new ArrayList<>();
    private List<StatusUpdatedListener> listeners = new ArrayList<>();
	private int nmbrOfProgressBars = 2;
	private int id = 0;
	private int cntr = 0;
	
    public static void main (String[] args) {
        Main main = new Main();
		main.startThread();
    }
	
	public void startThread(){
		for ( int i = 0; i < nmbrOfProgressBars ; i++){	
			this.registerStatusListener(new PrintUpdateStatus());

			Status status = new Status(this);
			status.start();	
		}	
	}

    public void registerStatusListener (StatusUpdatedListener listener) {
        this.listeners.add(listener);
    }

    protected void notifyStatusListeners (Status status) {
        this.listeners.forEach(listener -> listener.onStatusUpdated(status));
    }

	
	public void interupted(){
		System.out.println("thread interrupted");
	}
	
	public void progMade(Status status){
		cntr++;
		if (cntr >= nmbrOfProgressBars){
			this.listeners.forEach(listener -> listener.onStatusUpdated(status));
			cntr = 0;
		}

	}
	
}