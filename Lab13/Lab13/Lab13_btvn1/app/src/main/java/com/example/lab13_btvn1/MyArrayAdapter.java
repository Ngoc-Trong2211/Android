package com.example.lab13_btvn1;

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

public class MyArrayAdapter extends ArrayAdapter<Iphone> {
    Activity context;
    int idLayout;
    ArrayList<Iphone> arrayList;

    public MyArrayAdapter(Activity context, int idLayout, ArrayList<Iphone> arrayList) {
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
        Iphone iphone = arrayList.get(position);
        ImageView imageView = convertView.findViewById(R.id.imageView2);
        imageView.setImageResource(iphone.getImg());
        TextView textView = convertView.findViewById(R.id.textView2);
        textView.setText(iphone.getName());
        TextView textView1 = convertView.findViewById(R.id.textView3);
        textView1.setText("Gia ban: "+iphone.getPrice());
        return convertView;
    }
}
