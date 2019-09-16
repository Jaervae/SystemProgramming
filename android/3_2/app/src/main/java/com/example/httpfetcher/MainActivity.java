package com.example.httpfetcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ThreadClass.ThreadClassReporterInterFace{

    private String http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startThread();
            }
        });
    }

    public void startThread(){
        EditText editText = (EditText)findViewById(R.id.editText);
        http = editText.getText().toString();

        ThreadClass threadClass = new ThreadClass(this);
        threadClass.start();


    }

    @Override
    public void interupted() {
        System.out.println("Thread was interupted");
    }

    @Override
    public String getHTTP() {
        return http;
    }

    @Override
    public void printHTTP(String mHttp) {

        myRunnableFunc(mHttp);
    }

    public void myRunnableFunc(final String mString){
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setText(mString);
            }
        });
    }
}
