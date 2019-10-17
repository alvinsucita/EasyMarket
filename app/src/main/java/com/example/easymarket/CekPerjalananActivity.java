package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;

public class CekPerjalananActivity extends AppCompatActivity {

    Button btnCekPerjalan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_perjalanan);

        btnCekPerjalan = findViewById(R.id.btnCekPerjalanan);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(5, Color.BLACK);
        drawable.setCornerRadius(15);
        btnCekPerjalan.setBackground(drawable);

    }
}
