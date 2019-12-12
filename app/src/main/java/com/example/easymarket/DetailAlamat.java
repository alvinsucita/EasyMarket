package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class DetailAlamat extends AppCompatActivity {

    EditText labelalamat,nama,nohp,alamat,kota,kode;
    Spinner pembayaran;
    ArrayList<String> spinnerArray =  new ArrayList<>();
    Button konfirmasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alamat);
        nama=findViewById(R.id.editText3);
        nohp=findViewById(R.id.editText4);
        kota=findViewById(R.id.editText5);
        kode=findViewById(R.id.editText6);
        alamat=findViewById(R.id.editText7);
        pembayaran = findViewById(R.id.sppembayaran);
        konfirmasi=findViewById(R.id.button3);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.WHITE);
        nama.setBackground(drawable);
        nohp.setBackground(drawable);
        kota.setBackground(drawable);
        kode.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(50);
        drawable2.setColor(Color.WHITE);
        alamat.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        pembayaran.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setCornerRadius(100);
        drawable4.setColor(Color.BLACK);
        konfirmasi.setBackground(drawable4);

        spinnerArray.add("COD");
        spinnerArray.add("PayPal");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.sppembayaran);
        sItems.setAdapter(adapter);

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

    public void toDetailPengiriman(View view) {
        Intent i = new Intent(DetailAlamat.this,NotaActivity.class);
        startActivity(i);
    }
}
