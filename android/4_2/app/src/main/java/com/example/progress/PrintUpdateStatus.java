package com.example.progress;

public class PrintUpdateStatus implements StatusUpdatedListener{
	@Override
    public void onStatusUpdated (Status status) {

        System.out.println("On_Progress: " + status.getStatus() + "%");

		if(status.getStatus() == 100){
			System.out.println("On_Completed");
		}
    }
}