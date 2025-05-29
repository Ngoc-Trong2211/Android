package com.example.lab12_2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    EditText editHour, editMinute, editInputWork;
    Button btnAdd;
    TextView txtToday;
    ArrayList<String> arrayList;

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

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String day = simpleDateFormat.format(date);

        editHour = findViewById(R.id.editHour);
        editMinute = findViewById(R.id.editMinute);
        editInputWork= findViewById(R.id.editInputWork);
        txtToday = findViewById(R.id.txtToday);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listView);

        txtToday.setText("Hom Nay: " + day);

        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editHour.getText().toString().equals("") || editMinute.getText().toString().equals("") || editInputWork.getText().toString().equals("")){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Thong tin chua du");
                    dialog.setMessage("Vui long nhap du thong tin");
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    dialog.create().show();
                }
                else {
                    String work = "+ " + editInputWork.getText().toString() + " - " + editHour.getText().toString() + ":" + editMinute.getText().toString();
                    arrayList.add(work);
                    arrayAdapter.notifyDataSetChanged();
                    editMinute.setText("");
                    editHour.setText("");
                    editInputWork.setText("");
                }
            }
        });
    }
}