package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class DetailAlamat extends AppCompatActivity {

    EditText labelalamat,nama,nohp,alamat,kota,kode;
    Button konfirmasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alamat);
        labelalamat=findViewById(R.id.editText2);
        nama=findViewById(R.id.editText3);
        nohp=findViewById(R.id.editText4);
        kota=findViewById(R.id.editText5);
        kode=findViewById(R.id.editText6);
        alamat=findViewById(R.id.editText7);
        konfirmasi=findViewById(R.id.button3);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setStroke(5, Color.BLACK);
        konfirmasi.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setColor(Color.WHITE);
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setStroke(5, Color.BLACK);
        drawable2.setCornerRadius(15);
        labelalamat.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setColor(Color.WHITE);
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setStroke(5, Color.BLACK);
        drawable3.setCornerRadius(15);
        nama.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setColor(Color.WHITE);
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setStroke(5, Color.BLACK);
        drawable4.setCornerRadius(15);
        nohp.setBackground(drawable4);

        GradientDrawable drawable5 = new GradientDrawable();
        drawable5.setColor(Color.WHITE);
        drawable5.setShape(GradientDrawable.RECTANGLE);
        drawable5.setStroke(5, Color.BLACK);
        drawable5.setCornerRadius(15);
        alamat.setBackground(drawable5);

        GradientDrawable drawable6 = new GradientDrawable();
        drawable6.setColor(Color.WHITE);
        drawable6.setShape(GradientDrawable.RECTANGLE);
        drawable6.setStroke(5, Color.BLACK);
        drawable6.setCornerRadius(15);
        kode.setBackground(drawable6);

        GradientDrawable drawable7 = new GradientDrawable();
        drawable7.setColor(Color.WHITE);
        drawable7.setShape(GradientDrawable.RECTANGLE);
        drawable7.setStroke(5, Color.BLACK);
        drawable7.setCornerRadius(15);
        kota.setBackground(drawable7);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
