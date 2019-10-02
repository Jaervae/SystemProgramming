package com.example.publishandsubscribe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int[] intArray = new int[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filter("0");
        filter("1");
        filter("2");
        filter("3");
        filter("4");
        filter("5");
        filter("6");

        for (int i = 0 ; i < 8; i++ ){
            intArray[i]=0;
        }

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.text);
                textView.setText("Tasapelejä: " + intArray[0]);
                for (int i = 1 ; i < 8; i++ ){
                    if(i == 7)
                        textView.append("\nNoppaa heitety: " + intArray[i]+ " kertaa");
                    else
                        textView.append("\n"+ i + ": " + intArray[i]);
                }
            }
        });
    }

    public void filter(String dice){
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver,
                new IntentFilter(dice));
    }

    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String sDice = intent.getAction();
            int Dice =  Integer.parseInt(sDice);
            intArray[Dice]++;
            intArray[7]++;

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver);
    }
}
