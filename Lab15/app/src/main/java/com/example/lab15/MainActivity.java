package com.example.lab15;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editIdClass, editClassName, editSum;
    Button btnInsert, btnDelete, btnUpdate, btnQuery;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    SQLiteDatabase database;

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

        editIdClass = findViewById(R.id.editIdClass);
        editClassName = findViewById(R.id.editClassName);
        editSum = findViewById(R.id.editSum);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnQuery = findViewById(R.id.btnQuery);

        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        database = openOrCreateDatabase("qlysinhvien.db", MODE_PRIVATE, null);

        try {
            String sql = "CREATE TABLE tbllop (malop TEXT primary key, tenlop TEXT, siso INTEGER)";
            database.execSQL(sql);
        }
        catch (Exception e){
            Log.e("Error", "Table da ton tai!");
        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = editIdClass.getText().toString();
                String tenlop = editClassName.getText().toString();
                int siso = Integer.parseInt(editSum.getText().toString());
                ContentValues contentValues = new ContentValues();
                contentValues.put("malop", malop);
                contentValues.put("tenlop", tenlop);
                contentValues.put("siso", siso);
                if(database.insert("tbllop", null, contentValues) == -1){
                    Toast.makeText(MainActivity.this, "Fail to insert record", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, "Insert record sucessfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int siso = Integer.parseInt(editSum.getText().toString());
                String malop = editIdClass.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put("siso", siso);
                if (database.update("tbllop", contentValues, "malop = ?", new String[]{malop})==0) {
                    Toast.makeText(MainActivity.this, "No record to update", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, database.update("tbllop", contentValues, "malop = ?", new String[]{malop}) + " Record is updated", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = editIdClass.getText().toString();
                int n = database.delete("tbllop", "malop = ?", new String[]{malop});
                if (n==0){
                    Toast.makeText(MainActivity.this, "No record to delete", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, n + " Record is deleted", Toast.LENGTH_SHORT).show();
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                String data = "";
                Cursor cursor = database.query("tbllop", null, null, null, null, null, null, null);
                cursor.moveToNext();
                while (cursor.isAfterLast() == false){
                    data = cursor.getString(0) + " - " + cursor.getString(1) + " - " + cursor.getString(2);
                    cursor.moveToNext();
                    arrayList.add(data);
                }
                cursor.close();
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}