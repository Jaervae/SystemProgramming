package com.example.progress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Status.ThreadReporterInterface{

    private List<Status> statuses = new ArrayList<>();
    private List<StatusUpdatedListener> listeners = new ArrayList<>();
    private int nmbrOfProgressBars = 0;
    private int id = 0;
    private int cntr = 0;
    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            nmbrOfProgressBars++;
            Log.d("moro", "clicked");
            registerStatusListener(new PrintUpdateStatus());
            Status status = new Status(this);
            status.startThread();
        });
    }

    public void registerStatusListener (StatusUpdatedListener listener) {
        this.listeners.add(listener);
    }

    protected void notifyStatusListeners (Status status) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.listeners.forEach(listener -> listener.onStatusUpdated(status));
        }
    }
    public void interupted(){
        System.out.println("thread interrupted");
    }

    public void progMade(Status status){
        MainActivity.this.runOnUiThread(() -> {
            String txt = "On_Progress_" + status.getId() + ": " + status.getStatus() + "%";
            String txtPlus = txt + "\n" + textView.getText().toString();
            textView.setText(txtPlus);
            System.out.println(txt);
            if(status.getStatus() == 100){
                textView.setText("On_Completed_"+ status.getId()+"\n" + txtPlus );
                System.out.println("On_Completed");
            }
        });
    }
}
