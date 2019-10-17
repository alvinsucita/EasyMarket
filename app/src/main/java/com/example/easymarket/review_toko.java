package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class review_toko extends AppCompatActivity {

    TextView namauser, ratinguser, review;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_toko);

        namauser = findViewById(R.id.tvuname);
        review = findViewById(R.id.tvureview);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(5, Color.BLACK);
        drawable.setCornerRadius(15);
        namauser.setBackground(drawable);
        review.setBackground(drawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.temp_tabtoko,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }
        if(item.getItemId()==R.id.page){
            Intent i = new Intent(review_toko.this,pagetoko.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.product){
            Intent i = new Intent(review_toko.this,TempProduct.class);
            startActivity(i);
        }else if(item.getItemId() == R.id.info){
            Intent i = new Intent(review_toko.this,info_toko.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
