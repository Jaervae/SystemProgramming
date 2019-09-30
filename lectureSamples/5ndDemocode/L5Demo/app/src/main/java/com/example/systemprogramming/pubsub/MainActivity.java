package com.example.systemprogramming.pubsub;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver,
                new IntentFilter("Toy car"));
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver,
                new IntentFilter("Doll"));
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver,
                new IntentFilter("Teddy Bear"));

        findViewById(R.id.dollPress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DollActivity_.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver);
    }

    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String product = intent.getAction();
            String status = intent.getStringExtra("status");
            String time = intent.getStringExtra("manufactTime");

            TextView textView = findViewById(R.id.textView);
            textView.append("\nProduct " + product + " " + status + " " + time);
        }
    };
}
