package com.example.lab07_3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {

    EditText editGetA, editGetB;
    Button btnSum, btnSubtraction;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editGetA = findViewById(R.id.editGetA);
        editGetB = findViewById(R.id.editGetB);
        btnSum = findViewById(R.id.btnSum);
        btnSubtraction = findViewById(R.id.btnSubtraction);

        intent = getIntent();
        double a = intent.getDoubleExtra("a", 0);
        double b = intent.getDoubleExtra("b", 0);
        editGetA.setText(a+"");
        editGetB.setText(b+"");

//        btnSubtraction.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}