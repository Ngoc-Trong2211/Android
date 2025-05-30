package com.example.lab13_nc_btvn;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

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
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(idLayout, null);
        }

        ImageView imageView = row.findViewById(R.id.img);
        imageView.setImageResource(arrayList.get(position).getItem());

        return row;
    }
}
