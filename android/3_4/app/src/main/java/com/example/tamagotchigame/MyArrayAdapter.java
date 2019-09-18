package com.example.tamagotchigame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<ThreadClass>{

    static final int VIEW_ALIVE = 0;
    static final int VIEW_DEAD = 1;
    static final int VIEW_COUNT = 2;

    public MyArrayAdapter(@NonNull Context context, ArrayList<ThreadClass>list){
        super(context,0,list);
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_COUNT;
    }


    @Override
    public int getItemViewType(int position) {
        ThreadClass list = getItem(position);
        Log.d("TEST", "getItemViewType"); //4
        if (list.getHungryState() < 0 || list.getHungryState() > 20){

            return VIEW_DEAD;
        }
        else
        {
            return VIEW_ALIVE;
        }
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ThreadClass list = getItem(position);

        if (convertView==null){
            int layoutId = 0;
            if (getItemViewType(position)==VIEW_ALIVE){
                layoutId = R.layout.alive_list_item;
            }
            else
                layoutId = R.layout.dead_list_item;

            convertView = LayoutInflater.from(getContext()).inflate(layoutId,parent,false);
        }

        TextView label = convertView.findViewById(R.id.listItemLabel);
        TextView foodLeft = convertView.findViewById(R.id.foodLeftLabel);
        label.setText("Tamagotchi: " + Integer.toString(list.getNumber()));
        foodLeft.setText("Food left: " + Integer.toString(list.getHungryState()));
        return convertView;
    }


}