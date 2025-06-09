package com.example.lab24;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Tygia> {
    Activity context;
    int resource;
    List<Tygia> objects;

    public MyArrayAdapter(Activity context, int resource, List<Tygia> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        Tygia tygia = this.objects.get(position);

        ImageView imgHinh = item.findViewById(R.id.imgHinh);
        TextView txtType = item.findViewById(R.id.Type);
        TextView txtMuaTM = item.findViewById(R.id.MuaTM);
        TextView txtBanTM = item.findViewById(R.id.BanTM);
        TextView txtMuaCK = item.findViewById(R.id.MuaCK);
        TextView txtBanCK = item.findViewById(R.id.BanCK);

        imgHinh.setImageBitmap(tygia.getBitmap());
        txtType.setText(tygia.getType());
        txtMuaTM.setText(tygia.getMuatienmat());
        txtBanTM.setText(tygia.getBantienmat());
        txtMuaCK.setText(tygia.getMuack());
        txtBanCK.setText(tygia.getBanck());

        return item;
    }
}
