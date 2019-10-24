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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CekPerjalananActivity extends AppCompatActivity {

    Button btnCekPerjalan;
    TextView tvID,tvAlamat,tvKeterangan;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_perjalanan);

        tvID = findViewById(R.id.tvIdBarangCekPerjalanan);
        tvAlamat = findViewById(R.id.tvAlamatCekPerjalanan);
        //tvJasaPengirim = findViewById(R.id.spinnerspinnerJasaPengirimCekPerjalan);
        btnCekPerjalan = findViewById(R.id.btnCekPerjalanan);
        tvKeterangan = findViewById(R.id.tvKeteranganCekPerjalanan);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(5, Color.BLACK);
        drawable.setCornerRadius(15);
        btnCekPerjalan.setBackground(drawable);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Spinner element
        spinner = findViewById(R.id.spinnerJasaPengirimCekPerjalan);
        // Spinner click listener
        //spinner.setOnItemSelectedListener();
        List<String> categories = new ArrayList<String>();
        categories.add("JNE");
        categories.add("TIKI");
        categories.add("POS INDONESIA");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            // On selecting a spinner item
//            String item = parent.getItemAtPosition(position).toString();
//
//            // Showing selected spinner item
//            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
//        }
//        public void onNothingSelected(AdapterView<?> arg0) {
//            // TODO Auto-generated method stub
//        }

        final Boolean cek = true;
        btnCekPerjalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cek){
                    tvKeterangan.setText("Terkirim");
                }
                else{
                    tvKeterangan.setText("BelumTerkirim");
                }
            }
        });


    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            if(id == android.R.id.home){
                this.finish();
            }
            return super.onOptionsItemSelected(item);
        }


}
