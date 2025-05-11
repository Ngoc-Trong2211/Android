package com.example.lab04_2;

import static java.lang.Math.pow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText editName, editHeight, editWidth, editBMI, editResult;

    Button button;

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

        editName = findViewById(R.id.editName);
        editHeight = findViewById(R.id.editHeight);
        editWidth = findViewById(R.id.editWidth);
        editBMI = findViewById(R.id.editBMI);
        editResult = findViewById(R.id.EditResult);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                double height = Double.parseDouble(editHeight.getText().toString());
                if (height==0){
                    Toast.makeText(MainActivity.this, "Chieu cao khong duoc bang 0!", Toast.LENGTH_SHORT).show();
                    return;
                }
                double width = Double.parseDouble(editWidth.getText().toString());
                String decimalBMI = decimalFormat.format(width / pow(height, 2));
                double bmi = Double.parseDouble(decimalBMI);
                String res = "";
                if (bmi < 18) res = "Nguoi gay";
                else if (bmi >= 18 && bmi < 24.9) res = "Nguoi binh thuong";
                else if (bmi >= 25 && bmi < 29.9) res = "Nguoi beo phi do I";
                else if (bmi >= 30 && bmi < 34.9) res = "Nguoi beo phi do II";
                else res = "Nguoi beo phi do III";
                editBMI.setText(bmi+"");
                editResult.setText(res);
            }
        });
    }
}