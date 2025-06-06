package com.example.lab20;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    // Khai báo MyAsyncTask
    private MyAsyncTask myTask;

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

        btnStart = findViewById(R.id.btnStart);

        // Thiết lập sự kiện click cho nút bấm
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO: Tự động tạo phương thức
                doStart();
            }
        });
    }

    private void doStart() {
        // Truyền this (MainActivity hiện tại) qua background thread
        // Tạo mới một MyAsyncTask và truyền context của MainActivity
        myTask = new MyAsyncTask(this);

        // Kích hoạt tiến trình, onPreExecute của myTask sẽ thực thi trước
        myTask.execute();
    }
}
