package com.example.lab13_btvn1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] name = {"abc", "xyz", "qwe"};
    String[] price = {"10", "11", "12"};
    ArrayList<Iphone> arrayList;
    MyArrayAdapter arrayAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        arrayAdapter = new MyArrayAdapter(MainActivity.this, R.layout.item, arrayList);
        listView.setAdapter(arrayAdapter);

        for (int i=0; i<name.length; i++){
            Iphone iphone = new Iphone();
            iphone.setImg(R.drawable.img);
            iphone.setName(name[i]);
            iphone.setPrice(Integer.parseInt(price[i]));
            arrayList.add(iphone);
        }
    }
}