package com.example.tiisuspammer;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String text = "";

    //Initially setting the flag as true
    private volatile boolean flag = true;

    //This method will set flag as false
    public void stopRunning()
    {
        flag = false;
    }

    //This method will set flag as true
    public void startRunning()
    {
        flag = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTasker asyncTasker = new AsyncTasker();
                UpdateText("START");
                startRunning();
                asyncTasker.execute("");

            }
        });

        findViewById(R.id.buttonStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateText("STOP");
                    stopRunning();

            }
        });

    }

    public void UpdateText(String addedText){

        setText( "\n"  + addedText + getText());
        TextView textView =(TextView)findViewById(R.id.textView2);
        textView.setText(text);

    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private class AsyncTasker extends AsyncTask<String, String, String> {

        private int resp;

        @Override
        protected String doInBackground(String... params) {

            try {
                while (flag) {
                    publishProgress("Sleeping..."); // Calls onProgressUpdate()
                    Thread.sleep(1000);
                    resp++;

                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            String sResp = Integer.toString(resp);
            return sResp;
        }


        @Override
        protected void onPostExecute(String result) {

        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {
            UpdateText("Tiisu, We don't wanna to hear more!!");

        }
    }

}
