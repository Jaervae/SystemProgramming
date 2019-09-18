package com.example.tamagotchigame;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements ThreadClass.TamagotchiThreadReporterInterface {

    private int tamagotchiAmmount;
    private final int healthAtBegin = 10;
    ArrayList<ThreadClass> list = new ArrayList<ThreadClass>();
    private int id;
    private int cntr = 0;
    private boolean gameOn = false;
    Boolean[] booleanArray;
    MyArrayAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent mIntent = getIntent();
        tamagotchiAmmount = mIntent.getIntExtra("nmbr", 5);
        booleanArray = new Boolean[tamagotchiAmmount];
        listView = (ListView)findViewById(R.id.listViewGame);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                booleanArray[position] = true;
            }
        });
        GameStarts();
    }

    public void GameStarts(){

        id = 0;
        Arrays.fill(booleanArray, Boolean.FALSE);
        for ( int i = 0; i < tamagotchiAmmount ; i++)
        {
            createTamagotchi(i);
            ThreadClass object = new ThreadClass(this);
            object.start();
        }
        gameOn = true;
        NotifyMainThread();



    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    public void createTamagotchi(int i){
        list.add(new ThreadClass(this));
        list.get(i).setNumber(i);
        list.get(i).setHungryState(10);
        list.get(i).setStatus("Alive and happy");
        try {
            Thread.sleep(100);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void interupted(){
        System.out.println("thread interrupted");
    }

    public int getOwnTamagotchi(){
        int temp = this.id;
        id++;
        return temp;
    }

    public void dataChanged(int id,int hungry){
        list.get(id).setHungryState(hungry);
        list.get(id).setStatus(CheckIfAlive(id));
        NotifyMainThread();
    }

    public boolean checkIfNeedToEat(int id){
        if(booleanArray[id]){
            booleanArray[id] = false;
            return true;
        }
        return false;
    }

    public void NotifyMainThread(){

        GameActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                boolean allDied = true;
                for ( int i = 0; i < tamagotchiAmmount ; i++)
                {
                    if(list.get(i).getHungryState() >= 0 && list.get(i).getHungryState() <= 20){
                        allDied = false;
                        break;
                    }
                }

                adapter = new MyArrayAdapter(getApplicationContext(), list);
                listView.setAdapter(adapter);

                if(allDied){
                    gameOn = false;
                    GameOver();
                }

            }
        });
    }

    public void GameOver(){
        customDialog("GAME OVER", "Kaikki tamagotchit kuolivat :(" , "cancelMethod");

    }
    private void cancelMethods(){
        finish();
    }


    public void customDialog(String title, String message, final String cancelMethod){
        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(this);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);
        builderSingle.setNegativeButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (cancelMethod.equals("cancelMethod")){
                            cancelMethods();
                        }
                    }
                });
        builderSingle.show();
    }


    public int GetHungryStatus(int id){
        return list.get(id).getHungryState();
    }

    public String CheckIfAlive (int id){
        if (GetHungryStatus(id) < 0 ){
            return " was starved to dead :(";
        }
        if (GetHungryStatus(id) > 20 ){
            return " was overfed and now is dead.";
        }
        return " is happy and alive!";
    }

}
