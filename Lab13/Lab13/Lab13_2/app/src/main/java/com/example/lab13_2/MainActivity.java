package com.example.lab13_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String[] name = {"Anh 1", "Anh 2", "Anh 3", "Anh 4", "Anh 5", "Anh 6", "Anh 7", "Anh 8", "Anh 9"};
    public static int[] img = {R.drawable.img, R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_6, R.drawable.img_7, R.drawable.img_8};
    ArrayList<Image> arrayList;
    MyArrayAdapter arrayAdapter;
    GridView gridView;

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

        gridView = findViewById(R.id.gridView);
        arrayList = new ArrayList<>();
        arrayAdapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_item, arrayList);
        gridView.setAdapter(arrayAdapter);

        for(int i=0; i<img.length; i++){
            Image image = new Image();
            image.setImg(img[i]);
            image.setName(name[i]);
            arrayList.add(image);
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("vitri", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}