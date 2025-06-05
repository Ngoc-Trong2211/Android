package com.example.lab16;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editA, editB, editResult;
    Button btnSum, btnClear;
    String lichsu = "";

    TextView txtLichsu;

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

        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        editResult = findViewById(R.id.editResult);
        btnClear = findViewById(R.id.btnClear);
        btnSum = findViewById(R.id.btnSum);
        txtLichsu = findViewById(R.id.txtHistory);

        SharedPreferences sharedPreferences = getSharedPreferences("mysave", MODE_PRIVATE);
        lichsu = sharedPreferences.getString("ls", "");
        txtLichsu.setText(lichsu);

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(editA.getText().toString());
                int b = Integer.parseInt(editB.getText().toString());
                int kq = a+b;
                editResult.setText(kq + "");
                lichsu += a + " + " + b + " = " + kq;
                txtLichsu.setText(lichsu);
                lichsu += "\n";
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lichsu = "";
                txtLichsu.setText(lichsu);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("mysave", MODE_PRIVATE);
        SharedPreferences.Editor myedit = sharedPreferences.edit();
        myedit.putString("ls", lichsu);
        myedit.commit();
    }
}