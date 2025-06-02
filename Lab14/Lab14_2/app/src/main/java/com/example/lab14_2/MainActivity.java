package com.example.lab14_2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> arrayList, arrayList1, arrayList2;
    MyArrayAdapter arrayAdapter, myArrayAdapter1, myArrayAdapter2;
    TabHost tabHost;
    TabHost.TabSpec tab1, tab2, tab3;
    ListView listView, listView2, listView3;
    EditText editText;

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

        editText = findViewById(R.id.editTextText);
        listView = findViewById(R.id.listView);
        listView2 = findViewById(R.id.listView2);
        listView3 = findViewById(R.id.listView3);
        tabHost = findViewById(R.id.tabHost);

        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();

        tab1 = tabHost.newTabSpec("t1");
        tab2 = tabHost.newTabSpec("t2");
        tab3 = tabHost.newTabSpec("t3");
        
        controllEvent();
        addEvent();
    }

    public void addEvent(){
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("t1")){
                    arrayList.clear();
                    arrayList.add(new Item("12345", 0, "abcdefghiklm"));
                    arrayList.add(new Item("12322", 1, "Em cua ngay hom qua"));
                    arrayAdapter.notifyDataSetChanged();
                }
                if (tabId.equalsIgnoreCase("t2")){
                    arrayList1.clear();
                    arrayList1.add(new Item("12389", 0, "say tinh"));
                    arrayList1.add(new Item("12399", 1, "chan dang"));
                    myArrayAdapter1.notifyDataSetChanged();
                }
                if (tabId.equalsIgnoreCase("t3")){
                    arrayList2.clear();
                    arrayList2.add(new Item("12355", 0, "wqerty"));
                    arrayList2.add(new Item("12333", 1, "oiuhgfcx"));
                    myArrayAdapter2.notifyDataSetChanged();
                }
            }
        });
    }

    public void controllEvent(){
        tabHost.setup();
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(R.drawable.img));
        tabHost.addTab(tab1);
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.img_1));
        tabHost.addTab(tab2);
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("", getResources().getDrawable(R.drawable.img_2));
        tabHost.addTab(tab3);

        arrayAdapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_item, arrayList);
        myArrayAdapter1 = new MyArrayAdapter(MainActivity.this, R.layout.layout_item, arrayList1);
        myArrayAdapter2 = new MyArrayAdapter(MainActivity.this, R.layout.layout_item, arrayList2);

        listView.setAdapter(arrayAdapter);
        listView2.setAdapter(myArrayAdapter1);
        listView3.setAdapter(myArrayAdapter2);
    }
}