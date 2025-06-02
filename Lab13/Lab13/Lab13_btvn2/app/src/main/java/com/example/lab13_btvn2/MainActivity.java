package com.example.lab13_btvn2;

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
    String[] name = {"abc", "xyz", "ghj"};
    String[] phoneNumber = {"123456789", "987654321", "123459876"};
    ArrayList<Custom> arrayList;
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
            arrayList.add(new Custom(name[i], phoneNumber[i]));
        }
    }
}