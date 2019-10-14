package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class InfoComment extends AppCompatActivity {

    TextView isi,balasan,tambahbalas,tambahkomen;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_comment);
        isi=findViewById(R.id.tvIsiComment);
        balasan=findViewById(R.id.tvReply);
        tambahkomen=findViewById(R.id.etTambahKomentar);
        tambahbalas=findViewById(R.id.etTambahBalasan);
        send=findViewById(R.id.btnSend);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(5, Color.BLACK);
        drawable.setCornerRadius(15);
        isi.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setColor(Color.WHITE);
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setStroke(5, Color.BLACK);
        drawable2.setCornerRadius(15);
        balasan.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setColor(Color.WHITE);
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setStroke(5, Color.BLACK);
        drawable3.setCornerRadius(15);
        tambahbalas.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setColor(Color.WHITE);
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setStroke(5, Color.BLACK);
        drawable4.setCornerRadius(15);
        tambahkomen.setBackground(drawable4);

        GradientDrawable drawable5 = new GradientDrawable();
        drawable5.setColor(Color.WHITE);
        drawable5.setShape(GradientDrawable.OVAL);
        drawable5.setStroke(5, Color.BLACK);
        send.setBackground(drawable5);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu2,menu);

        menu.getItem(1).setVisible(true);
        menu.getItem(0).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id== android.R.id.home){
            this.finish();
        }
        else if(id==R.id.menuInfo){
            Intent i = new Intent(InfoComment.this,InfoBarang.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
