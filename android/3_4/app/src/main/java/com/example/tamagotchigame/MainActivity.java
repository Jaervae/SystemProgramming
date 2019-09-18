package com.example.tamagotchigame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText)findViewById(R.id.editTextMain);
        Button button = (Button)findViewById(R.id.buttonStartMain);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String t = editText.getText().toString();
                    int nmbr = Integer.parseInt(t);
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("nmbr", nmbr);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Maybe there was non numeric symbols?", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}