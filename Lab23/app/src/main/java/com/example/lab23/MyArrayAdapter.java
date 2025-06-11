package com.example.lab23;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<List> {
    Activity context;
    ArrayList<List> arr;
    int layoutID;

    public MyArrayAdapter(Activity context, int layoutID, ArrayList<List> arr) {
        super(context, layoutID, arr);
        this.context = context;
        this.layoutID = layoutID;
        this.arr = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);

        final List item = arr.get(position);

        // Tìm các thành phần giao diện trong layout_listview.xml
        ImageView imgItem = convertView.findViewById(R.id.imageView);
        imgItem.setImageBitmap(item.getImg());

        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        txtTitle.setText(item.getTitle());

        TextView txtInfo = convertView.findViewById(R.id.txtInfo);
        txtInfo.setText(item.getInfo());

        // Thiết lập sự kiện click để mở liên kết bài báo
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
