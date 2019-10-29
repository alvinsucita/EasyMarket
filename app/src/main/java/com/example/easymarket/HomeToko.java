package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(5, Color.BLACK);
        drawable.setCornerRadius(15);
        hnamatoko.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setColor(Color.WHITE);
        drawable2.setShape(GradientDrawable.OVAL);
        drawable2.setStroke(10, Color.BLACK);
        btambah.setBackground(drawable2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);

        menu.getItem(0).setVisible(false);
        menu.getItem(1).setVisible(false);
        menu.getItem(2).setVisible(false);
        menu.getItem(3).setVisible(false);
        menu.getItem(4).setVisible(false);
        menu.getItem(5).setVisible(false);
        menu.getItem(6).setVisible(false);
        menu.getItem(7).setVisible(true);
        menu.getItem(8).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itemLogin){
            Intent i = new Intent(HomeToko.this,Login.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemLogout){
            Intent i = new Intent(HomeToko.this,Home.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemEditToko){
            Intent i = new Intent(HomeToko.this,EditInfoToko.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void tambah(View view){
        Intent i = new Intent(HomeToko.this,TambahBarang.class);
        startActivity(i);
    }
}

