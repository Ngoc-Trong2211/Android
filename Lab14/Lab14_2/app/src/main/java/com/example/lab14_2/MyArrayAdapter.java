package com.example.lab14_2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Item> {
    Activity context;
    int idLayout;
    ArrayList<Item> arrayList;

    public MyArrayAdapter(Activity context, int idLayout, ArrayList<Item> arrayList) {
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
        Item item = arrayList.get(position);
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(item.getId());
        TextView textView1 = convertView.findViewById(R.id.textView2);
        textView1.setText(item.getName());
        ImageButton imageButton = convertView.findViewById(R.id.imageButton2);
        int img = item.getImg();
        if (img==1) imageButton.setImageResource(R.drawable.img_2);
        else imageButton.setImageResource(R.drawable.img_3);
        return convertView;
    }
}
