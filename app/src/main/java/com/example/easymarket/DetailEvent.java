package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class DetailEvent extends AppCompatActivity {

    TextView deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        deskripsi = findViewById(R.id.tvDeskripsiEvent);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(50);
        drawable.setStroke(8, Color.LTGRAY);
        drawable.setColor(Color.WHITE);
        deskripsi.setBackground(drawable);
    }
}
