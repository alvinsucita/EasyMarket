package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

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

public class RefundActivity extends AppCompatActivity {

    Button btnSubmit;
    TextView tvID,tvNama,tvEmail,tvRekening;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);

        btnSubmit = findViewById(R.id.btnSubmit);
        tvNama = findViewById(R.id.tvNamaPemesan);
        tvID = findViewById(R.id.tvIdBarang);
        tvEmail = findViewById(R.id.tvEmail);
        tvRekening = findViewById(R.id.tvRekening);

        // Spinner element
        spinner = findViewById(R.id.spinnerPilihBank);
        // Spinner click listener
        //spinner.setOnItemSelectedListener();
        List<String> categories = new ArrayList<String>();
        categories.add("BCA");
        categories.add("MANDIRI");
        categories.add("DANAMON");
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

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(5, Color.BLACK);
        drawable.setCornerRadius(15);
        btnSubmit.setBackground(drawable);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvNama.getText().toString() == "" && tvID.getText().toString() == "" && tvEmail.getText().toString() == ""){
                    Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_LONG).show();
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
