package com.example.systemprogramming.pubsub;

import android.app.Application;

public class PubSubApplication extends Application {

    ToyFactory factory = new ToyFactory();

    @Override
    public void onCreate() {
        super.onCreate();
        factory.setContext(this);
        factory.start();
    }
}
