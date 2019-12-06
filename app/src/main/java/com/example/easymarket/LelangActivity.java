package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class LelangActivity extends AppCompatActivity {

    EditText search;
    RecyclerView rv;
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lelang);

        Intent i = getIntent();
        listClassBarang = (ArrayList<ClassBarang>) i.getSerializableExtra("listClassBarang");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        search=findViewById(R.id.etSearchLelang);
        rv=findViewById(R.id.rvlelang);

        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterBarangLelang adapterBarangLelang = new AdapterBarangLelang(listClassBarang);
        rv.setAdapter(adapterBarangLelang);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(15);
        search.setBackground(drawable);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void toChat(View view) {
        Intent i = new Intent(LelangActivity.this,ChatRoom.class);
        startActivity(i);
    }
}
