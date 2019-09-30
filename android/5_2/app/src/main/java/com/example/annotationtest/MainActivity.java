package com.example.annotationtest;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById(R.id.textView) // Injects R.id.myTextView
            TextView result;

    void myMethod() {
        doInUiThread("hello", 42);
    }

    @UiThread
    void doInUiThread(String aParam, long anotherParam) {
        result.setText(Integer.toHexString(12));
    }
}
