package com.example.annotations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.textViewBG)
    TextView textViewBG;
    @ViewById(R.id.textViewAT)
    TextView textViewAT;

    @Click({R.id.addButtonBG})
    void addTasksBG(){
        bgMethod();
    }

    @Click({R.id.addButtonAT})
    void addTaskAT(){
        atMethod();
    }

    void bgMethod() {
        someBackgroundWork("BG.Task", 42);
    }

    void atMethod(){
        doInUiThread("AT.Task");
    }

    @Background
    void someBackgroundWork(final String aParam, long anotherParam) {
        Thread threadID = Thread.currentThread();
        final long id = threadID.getId();
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5 ; i++){
                    textViewBG.setText(Integer.toString(i) + " " + aParam + "\n" + textViewBG.getText());
                }
                textViewBG.setText("Tämän toiminnon suoritti Thread:" + id + "\n" +textViewBG.getText());
            }
        });

    }

    @UiThread
    void doInUiThread(String aParam){
        for (int i = 0; i < 5 ; i++){
            textViewAT.setText(Integer.toString(i) + " " + aParam + "\n" + textViewAT.getText());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
