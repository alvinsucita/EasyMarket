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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Register1 extends AppCompatActivity {

    Button next;
    Spinner umur,daerahasal;
    RadioButton pria,wanita;
    EditText nama;
    ArrayList<String> spinnerArray =  new ArrayList<>();
    ArrayList<String> spinnerArray2 =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        next = findViewById(R.id.btnNextPage);
        umur = findViewById(R.id.spUmur);
        daerahasal = findViewById(R.id.spDaerahAsal);
        pria = findViewById(R.id.rbPria);
        wanita = findViewById(R.id.rbWanita);
        nama = findViewById(R.id.etNama);

        for (int i = 18; i < 61; i++) {
            spinnerArray.add(i + "");
        }

        spinnerArray2.add("Jakarta");
        spinnerArray2.add("Jawa Barat");
        spinnerArray2.add("Jawa Tengah");
        spinnerArray2.add("Jawa Timur");
        spinnerArray2.add("Yogyakarta");
        spinnerArray2.add("Madura");
        spinnerArray2.add("Banten");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spUmur);
        sItems.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems2 = (Spinner) findViewById(R.id.spDaerahAsal);
        sItems2.setAdapter(adapter2);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(5, Color.BLACK);
        drawable.setCornerRadius(15);
        drawable.setColor(Color.WHITE);
        nama.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setStroke(5, Color.BLACK);
        drawable2.setCornerRadius(15);
        drawable2.setColor(Color.WHITE);
        umur.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setStroke(5, Color.BLACK);
        drawable3.setCornerRadius(15);
        drawable3.setColor(Color.WHITE);
        pria.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setStroke(5, Color.BLACK);
        drawable4.setCornerRadius(15);
        drawable4.setColor(Color.WHITE);
        wanita.setBackground(drawable4);

        GradientDrawable drawable5 = new GradientDrawable();
        drawable5.setShape(GradientDrawable.OVAL);
        drawable5.setStroke(5, Color.BLACK);
        drawable5.setCornerRadius(15);
        drawable5.setColor(Color.WHITE);
        next.setBackground(drawable5);

        GradientDrawable drawable6 = new GradientDrawable();
        drawable6.setShape(GradientDrawable.RECTANGLE);
        drawable6.setStroke(5, Color.BLACK);
        drawable6.setCornerRadius(15);
        drawable6.setColor(Color.WHITE);
        daerahasal.setBackground(drawable6);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void toRegister2(View view) {
        if(nama.getText().toString().equals("")){
            Toast.makeText(this, "Isi Semua Field Terlebih Dahulu ! ", Toast.LENGTH_LONG).show();
        }
        else{
            if(pria.isChecked() || wanita.isChecked()){
                Intent i = new Intent(Register1.this,Register2.class);
                startActivity(i);
            }
            else{
                Toast.makeText(this, "Isi Semua Field Terlebih Dahulu ! ", Toast.LENGTH_LONG).show();
            }
        }
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
