package com.example.lab14_1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editA, editB;
    Button btn;
    TabHost tabHost;
    TabHost.TabSpec tab1, tab2;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
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
        controllEvent();
        addEvent();

    }

    private void addEvent(){
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editA = findViewById(R.id.editA);
                editB = findViewById(R.id.editB);
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                double a = Double.parseDouble(editA.getText().toString());
                double b = Double.parseDouble(editB.getText().toString());
                arrayList.add(a + " + " + b + " = " + (a+b));
                arrayAdapter.notifyDataSetChanged();
                editB.setText("");
                editA.setText("");
            }
        });
    }

    private void controllEvent(){
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();
        tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(R.drawable.img));
        tabHost.addTab(tab1);

        tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.img_1));
        tabHost.addTab(tab2);

    }
}