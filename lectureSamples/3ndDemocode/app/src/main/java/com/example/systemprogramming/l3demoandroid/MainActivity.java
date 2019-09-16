package com.example.systemprogramming.l3demoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleThread.ExampleThreadReporterInterface {

    ExampleThread thread = new ExampleThread(this);

    ArrayList<Thread> threads = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thread.start();

        threads.add(thread);
        threads.add(new Thread());
        threads.add(new Thread());
        threads.add(new Thread());



        NetworkThread networkThread = new NetworkThread();
        //networkThread.setObserver(this);
        //networkThread.setUrl("https://oamk.fi");
        //networkThread.start();
    }

    @Override
    public void soppOpen(/* ExampleThread caller */) {
        updateUIText("Sopp open");

     //   int index = threads.indexOf(caller);
    }

    private void updateUIText(final String newText) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView view = findViewById(R.id.textView);
                view.append(newText);
            }
        });
    }

    @Override
    public void soppClosed(/* ExampleThread caller */) {
        updateUIText("Sopp closed");
    }

    @Override
    public void interupted(/* ExampleThread caller */) {
        updateUIText("Sopp interrupted");
    }
}
