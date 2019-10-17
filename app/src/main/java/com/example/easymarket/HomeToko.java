package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeToko extends AppCompatActivity {

    TextView hnamatoko;
    Button btambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_toko);

        hnamatoko = findViewById(R.id.tvhnamatoko);
        btambah = findViewById(R.id.btntambah);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(5, Color.BLACK);
        drawable.setCornerRadius(15);
        hnamatoko.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.OVAL);
        drawable2.setStroke(5, Color.BLACK);
        drawable2.setCornerRadius(15);
        btambah.setBackground(drawable2);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.temp_usertoko,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.viewpage){
            Intent i = new Intent(HomeToko.this,pagetoko.class);
            startActivity(i);
        }else if(item.getItemId()==R.id.viewproduct){
            Intent i = new Intent(HomeToko.this,TempProduct.class);
            startActivity(i);
        }else if(item.getItemId() == R.id.viewreview){
            Intent i = new Intent(HomeToko.this,review_toko.class);
            startActivity(i);
        }else if(item.getItemId() == R.id.viewinfo) {
            Intent i = new Intent(HomeToko.this, EditInfoToko.class);
            startActivity(i);
        }else if(item.getItemId() == R.id.logout) {
            Intent i = new Intent(HomeToko.this, Login.class);
            startActivity(i);
        }
        return true;
    }

    public void tambah(View view){
        Intent i = new Intent(HomeToko.this,TambahBarang.class);
        startActivity(i);
    }


}
