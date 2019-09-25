public class PrintUpdateStatus implements StatusUpdatedListener{
	@Override
    public void onStatusUpdated (Status status) {
        // Print the number of animals
        System.out.println("On_Progress: " + status.getStatus() + "%");
    }
}