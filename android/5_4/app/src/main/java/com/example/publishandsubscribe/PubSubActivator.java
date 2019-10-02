package com.example.publishandsubscribe;

import android.app.Application;

public class PubSubActivator extends Application {

    DiceRoller diceRoller = new DiceRoller();

    @Override
    public void onCreate() {
        super.onCreate();
        diceRoller.setContext(this);
        diceRoller.start();
    }
}
