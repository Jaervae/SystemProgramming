package com.example.systemprogramming.pubsub;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Receiver;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_doll)
public class DollActivity extends AppCompatActivity {

    @ViewById(R.id.dollTextView)
    TextView textView;

    @Receiver(actions = "Doll", local = true)
    protected void dollCreated(Intent intent) {
        String product = intent.getAction();
        String status = intent.getStringExtra("status");
        String time = intent.getStringExtra("manufactTime");

        textView.append("\nProduct " + product + " " + status + " " + time);
    }

    @Background
    protected void doStuffBackground() {
      //  Thread.sleep(2000);
        runOnUi();
    }

    @UiThread
    protected void runOnUi(){

    }
}
