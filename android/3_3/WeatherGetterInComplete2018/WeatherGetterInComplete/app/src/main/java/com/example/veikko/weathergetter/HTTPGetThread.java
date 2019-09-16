package com.example.veikko.weathergetter;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPGetThread extends Thread {

    private String url;

    public interface OnRequestDoneInterface {

        void onRequestDone(String data);
    }

    public HTTPGetThread(String mUrl, OnRequestDoneInterface cb){

        callBackInterface = cb;
        url = mUrl;

    }

    OnRequestDoneInterface callBackInterface = null;

    public void run() {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(this.url);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

            }
            callBackInterface.onRequestDone(buffer.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
