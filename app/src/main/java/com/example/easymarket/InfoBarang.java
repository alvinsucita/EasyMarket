package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoBarang extends AppCompatActivity {

    TextView isi;
    Button share,chat,add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_barang);

        share=findViewById(R.id.btnShare);
        add=findViewById(R.id.btnAddToWishlist);
        chat=findViewById(R.id.btnPersonalChat);
        isi=findViewById(R.id.tvIsiDeskripsi);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setStroke(5, Color.BLACK);
        add.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setColor(Color.WHITE);
        drawable2.setShape(GradientDrawable.OVAL);
        drawable2.setStroke(5, Color.BLACK);
        share.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setColor(Color.WHITE);
        drawable3.setShape(GradientDrawable.OVAL);
        drawable3.setStroke(5, Color.BLACK);
        chat.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setColor(Color.WHITE);
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setStroke(5, Color.BLACK);
        drawable4.setCornerRadius(15);
        isi.setBackground(drawable4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu2,menu);

        menu.getItem(0).setVisible(true);
        menu.getItem(1).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        else if(id==R.id.menuComment){
            Intent i = new Intent(InfoBarang.this,InfoComment.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void toChat(View view) {
        Intent i = new Intent(InfoBarang.this,ChatRoom.class);
        startActivity(i);
    }
    public void preview(View view) {
        Intent i = new Intent(InfoBarang.this,PreviewActivity.class);
        startActivity(i);
    }
    public void beli(View view) {
        Intent i = new Intent(InfoBarang.this,TransactionActivity.class);
        startActivity(i);
    }
}
