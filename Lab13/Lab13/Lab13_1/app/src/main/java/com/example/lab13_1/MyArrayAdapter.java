package com.example.lab13_1;

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

public class MyArrayAdapter extends ArrayAdapter<Phone> {
    Activity context;
    int idLayout;
    ArrayList<Phone> arrayList;

    public MyArrayAdapter(Activity context, int idLayout, ArrayList<Phone> arrayList) {
        super(context, idLayout, arrayList);
        this.idLayout = idLayout;
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        convertView = layoutInflater.inflate(idLayout, null);
        Phone phone = arrayList.get(position);
        ImageView imageView = convertView.findViewById(R.id.imgPhone);
        imageView.setImageResource(phone.getImg());
        TextView textView = convertView.findViewById(R.id.txtPhone);
        textView.setText(phone.getName());
        return convertView;
    }
}
