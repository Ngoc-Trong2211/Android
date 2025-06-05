package com.example.lab17;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String DATABASE_NAME = "qlsach.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    private ListView listView;
    private ArrayList<String> mylist;
    private ArrayAdapter<String> myadapter;
    private SQLiteDatabase mydatabase;

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

        // Sao chép cơ sở dữ liệu từ assets vào bộ nhớ thiết bị
        processCopy();

        // Mở cơ sở dữ liệu
        mydatabase = openOrCreateDatabase("qlsach.db", MODE_PRIVATE, null);

        // Truy vấn dữ liệu từ bảng tblSach
        Cursor cursor = mydatabase.rawQuery("SELECT * FROM tblSach", null);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, mylist);
        listView = findViewById(R.id.listView);

        listView.setAdapter(myadapter);

        // Đọc dữ liệu từ cursor và thêm vào danh sách
        while (cursor.moveToNext()) {
            String data = cursor.getString(0) + " - " + cursor.getString(1) + " - " + cursor.getString(2);
            mylist.add(data);
        }
        cursor.close();

        // Cập nhật giao diện
        myadapter.notifyDataSetChanged();
    }

    // Lấy đường dẫn đến thư mục databases
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    // Sao chép cơ sở dữ liệu từ assets
    private void processCopy() {
        try {
            File dbFile = new File(getDatabasePath());
            if (!dbFile.exists()) {
                copyDatabaseFromAsset();
                Toast.makeText(this, "Copying success from Assets folder", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    // Thực hiện sao chép file từ assets sang thư mục databases
    private void copyDatabaseFromAsset() {
        try {
            InputStream inputStream = getAssets().open(DATABASE_NAME);
            String outputFileName = getDatabasePath();
            File file = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!file.exists()) {
                file.mkdir();
            }

            OutputStream outputStream = new FileOutputStream(outputFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}