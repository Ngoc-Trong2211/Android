package com.example.lab05_2;

import static java.lang.Math.sqrt;

import android.annotation.SuppressLint;
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

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText editA, editB, editC;
    TextView textView;
    Button btnContinue, btnResult, btnStop;

    @SuppressLint("MissingInflatedId")
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
        editC = findViewById(R.id.editC);
        btnContinue = findViewById(R.id.btnContinue);
        btnResult = findViewById(R.id.btnResult);
        btnStop = findViewById(R.id.btnStop);
        textView = findViewById(R.id.tvResult);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                double a = Double.parseDouble(editA.getText().toString());
                double b = Double.parseDouble(editB.getText().toString());
                double c = Double.parseDouble(editC.getText().toString());
                String result = "";
                if (a==0){
                    if (b==0){
                        if (c==0) result = "PT vo so nghiem";
                        else result = "PT vo nghiem";
                    }
                    else result = "PT co 1 nghiem, x = " + decimalFormat.format(-c/b);
                }
                else{
                    double delta = b*b - 4*a*c;
                    if (delta < 0) result = "PT vo so nghiem";
                    else if  (delta == 0) result = "PT co 1 nghiem, x = " + decimalFormat.format(-b/(2*a));
                    else result = "PT co 2 nghiem, x1 = " + decimalFormat.format((-b + sqrt(delta))/(2*a)) +
                                " x2 = " + decimalFormat.format((-b - sqrt(delta))/(2*a));
                }
                textView.setText(result);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editA.setText("");
                editB.setText("");
                editC.setText("");
                editA.requestFocus();
            }
        });
    }
}