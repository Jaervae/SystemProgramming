package com.example.progress;

import android.os.AsyncTask;

public class Status{

	public interface ThreadReporterInterface {
		void interupted();
		void progMade(Status status);
	}

	public Status(ThreadReporterInterface cb){
		callBackInterface = cb;
	}

	ThreadReporterInterface callBackInterface = null;

	private int status;
	private long id;
	
	public Status ( int status ){
		this.status = status;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public int getStatus () {
        return this.status;
    }
    public void setStatus (int status) {
        this.status = status;
    }

    public void startThread (){
		AsyncClass asyncTasker = new AsyncClass();
		asyncTasker.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
	}


	class AsyncClass extends AsyncTask<String,Status,String>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... strings) {
			Thread currentThread = Thread.currentThread();
			com.example.progress.Status objStatus = new com.example.progress.Status(0);
			objStatus.setId(currentThread.getId());
			try {
				while (true) {
					Thread.sleep(1000);
					int t = objStatus.getStatus() + 10;
					objStatus.setStatus(t);
					publishProgress(objStatus);
					if ( t == 100 ){
						break;
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(com.example.progress.Status... values) {
			super.onProgressUpdate(values);
			callBackInterface.progMade(values[0]);
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
		}
	}
	
}