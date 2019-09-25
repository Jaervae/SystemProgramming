public class PrintStatusListener implements StatusListener{
	@Override
	public void onStatusUpdated ( Status status ){
		System.out.println("On Proggress: " + status.getStatus() + "%");
	}
}