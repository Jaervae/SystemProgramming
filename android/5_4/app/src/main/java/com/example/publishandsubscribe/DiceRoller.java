package com.example.publishandsubscribe;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class DiceRoller extends Thread {

    private int p1;
    private int p2;
    final private int min = 1;
    final private int max = 6;


    private Context context = null;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        try{
            while (true) {
                p1 = randFunc();
                p2 = randFunc();

                if (p1 > p2){
                    returnScore(p1);
                }else if(p2 > p1){
                    returnScore(p2);
                }else
                    returnScore(0);
                sleep(100);
            }
        } catch (Exception e ) {e.printStackTrace();}
    }

    public int randFunc(){
        final int dice = new Random().nextInt((max - min) + 1) + min;
        return dice;
    }

    public void returnScore(int score){
        String firstToString = Integer.toString(score);
        Intent intent = new Intent(firstToString);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }
}
