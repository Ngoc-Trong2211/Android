package com.example.lab13_btvn2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Custom> {
    Activity context;
    int idLayout;
    ArrayList<Custom> arrayList;

    public MyArrayAdapter(Activity context, int idLayout, ArrayList<Custom> arrayList) {
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
        Custom custom = arrayList.get(position);
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText((position+1) + " - " + custom.getName());
        TextView textView1 = convertView.findViewById(R.id.textView2);
        textView1.setText(custom.getPhoneNumber());
        return convertView;
    }
}
