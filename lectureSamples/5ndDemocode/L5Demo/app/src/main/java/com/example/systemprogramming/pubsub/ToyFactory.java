package com.example.systemprogramming.pubsub;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Date;

public class ToyFactory extends Thread {

    private int counter = 0;

    private Context context = null;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        try{
            while (true) {
                counter++;
                if (counter % 2 == 0) {
                    productReady("Toy car");
                }
                if (counter % 5 == 0) {
                    productReady("Doll");
                }
                if (counter % 10 == 0) {
                    productReady("Teddy Bear");
                }
                sleep(1000);
            }
        } catch (Exception e ) {e.printStackTrace();}
    }

    private void productReady(String product) {
        Log.d("FACTORY", "Product:" + product + " ready.");

        Intent intent = new Intent(product);
        intent.putExtra("status", "ready");
        intent.putExtra("manufactTime", (new Date()).toString());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
