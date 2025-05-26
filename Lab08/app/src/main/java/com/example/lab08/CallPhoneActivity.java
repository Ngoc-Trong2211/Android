package com.example.lab08;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CallPhoneActivity extends AppCompatActivity {
    Button btnCallBack;
    ImageView imgPhone;
    EditText editInputPhoneCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_call_phone);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCallBack = findViewById(R.id.btnCallBack);
        imgPhone = findViewById(R.id.imgPhone);
        editInputPhoneCall = findViewById(R.id.editInputPhoneCall);

        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + editInputPhoneCall.getText().toString()));

                if (ActivityCompat.checkSelfPermission(CallPhoneActivity.this, Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CallPhoneActivity.this,
                            new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                    return;
                }

                startActivity(intent);
            }
        });

        btnCallBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}