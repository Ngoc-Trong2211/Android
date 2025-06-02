package com.example.lab13_2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Image> {
    Activity context;
    int idLayout;
    ArrayList<Image> arrayList;

    public MyArrayAdapter(Activity context, int idLayout, ArrayList<Image> arrayList) {
        super(context, idLayout, arrayList);
        this.arrayList = arrayList;
        this.context = context;
        this.idLayout = idLayout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);
        Image image = arrayList.get(position);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(image.getImg());
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(image.getName());
        return convertView;
    }
}
