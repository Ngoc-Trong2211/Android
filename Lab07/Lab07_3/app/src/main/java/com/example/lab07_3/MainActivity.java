package com.example.lab07_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editA, editB, eidtResult;
    Button btnRequest;

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

        editB = findViewById(R.id.editB);
        editA = findViewById(R.id.editA);
        eidtResult = findViewById(R.id.editResult);
        btnRequest = findViewById(R.id.btnRequest);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                double a = Double.parseDouble(editA.getText().toString());
                double b = Double.parseDouble(editB.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putDouble("a", a);
                bundle.putDouble("b", b);
                intent.putExtra("a", a);
                intent.putExtra("b", b);
                startActivity(intent);
            }
        });
    }
}