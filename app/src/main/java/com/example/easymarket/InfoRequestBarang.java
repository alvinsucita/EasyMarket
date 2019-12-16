package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;

public class InfoRequestBarang extends AppCompatActivity {

    Button batal, konfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_request_barang);

        batal = findViewById(R.id.btnBatal);
        konfirmasi = findViewById(R.id.btnKonfirmasi);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.BLACK);
        batal.setBackground(drawable);
        konfirmasi.setBackground(drawable);

    }
}
