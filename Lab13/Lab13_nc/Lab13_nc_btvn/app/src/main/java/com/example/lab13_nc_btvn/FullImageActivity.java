package com.example.lab13_nc_btvn;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FullImageActivity extends AppCompatActivity {
    ImageView fullImageView;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_item);

        fullImageView = findViewById(R.id.fullImageView);
        btnBack = findViewById(R.id.btnBack);

        int imageResId = getIntent().getIntExtra("image", 0);
        fullImageView.setImageResource(imageResId);

        btnBack.setOnClickListener(v -> finish());
    }
}
