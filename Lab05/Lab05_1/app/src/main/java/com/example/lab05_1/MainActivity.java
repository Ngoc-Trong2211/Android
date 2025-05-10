package com.example.lab05_1;

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

    EditText editD;
    TextView tvDtoA;

    Button btn;

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

        btn = findViewById(R.id.button);
        editD = findViewById(R.id.editD);
        tvDtoA = findViewById(R.id.tvDtoA);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int namDuong = Integer.parseInt(editD.getText().toString());
                int can = namDuong%10;
                int chi = namDuong%12;
                String resCan = "";
                String resChi = "";
                switch (can){
                    case 0:{
                        resCan = "Canh";
                        break;
                    }
                    case 1:{
                        resCan = "Tan";
                        break;
                    }
                    case 2:{
                        resCan = "Nham";
                        break;
                    }
                    case 3:{
                        resCan = "Quy";
                        break;
                    }
                    case 4:{
                        resCan = "Giap";
                        break;
                    }
                    case 5:{
                        resCan = "At";
                        break;
                    }
                    case 6:{
                        resCan = "Binh";
                        break;
                    }
                    case 7:{
                        resCan = "Dinh";
                        break;
                    }
                    case 8:{
                        resCan = "Mau";
                        break;
                    }
                    case 9:{
                        resCan = "Ky";
                        break;
                    }
                }
                switch (chi){
                    case 0:{
                        resChi = "Than";
                        break;
                    }
                    case 1:{
                        resChi = "Dau";
                        break;
                    }
                    case 2:{
                        resChi = "Tuat";
                        break;
                    }
                    case 3:{
                        resChi = "Hoi";
                        break;
                    }
                    case 4:{
                        resChi = "Ti";
                        break;
                    }
                    case 5:{
                        resChi = "Suu";
                        break;
                    }
                    case 6:{
                        resChi = "Dan";
                        break;
                    }
                    case 7:{
                        resChi = "Mao";
                        break;
                    }
                    case 8:{
                        resChi = "Thin";
                        break;
                    }
                    case 9:{
                        resChi = "Ty";
                        break;
                    }
                    case 10:{
                        resChi = "Ngo";
                        break;
                    }
                    case 11:{
                        resChi = "Mui";
                        break;
                    }
                }
                tvDtoA.setText(resCan + " " + resChi);
            }
        });
    }
}