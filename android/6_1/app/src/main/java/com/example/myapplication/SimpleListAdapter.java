package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class SimpleListAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> imageUrls;

    public SimpleListAdapter(Context baseContext, ArrayList<String> listItems) {
        super(baseContext,R.layout.listview_image,listItems);

        this.context = baseContext;
        this.imageUrls = listItems;

        inflater = LayoutInflater.from(baseContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (null == convertView){
            convertView = inflater.inflate(R.layout.listview_image,parent,false);
        }

        Glide
                .with(context)
                .load(imageUrls.get(position))
                .into((ImageView)convertView);

        return convertView;
    }
}
