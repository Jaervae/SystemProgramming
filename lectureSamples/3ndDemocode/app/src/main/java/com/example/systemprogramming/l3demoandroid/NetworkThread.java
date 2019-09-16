package com.example.systemprogramming.l3demoandroid;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkThread extends Thread {

    // Create an interface which can be used to report back the
    // result of the network request.

    // Set the url for the data you are going to fetch.


    @Override
    public void run() {
        try {
            URL url = new URL("https://oamk.fi");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Create inputstream by calling getInputStream for urlConnection
            // Convert the input stream to String.
            // Report the result via interface.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
