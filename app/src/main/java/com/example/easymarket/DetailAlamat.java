package com.example.easymarket;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailAlamat extends AppCompatActivity {

    EditText nama,nohp,alamat,kota,kode;
    Spinner pembayaran;
    ArrayList<String> spinnerArray =  new ArrayList<>();
    Button konfirmasi;
    String akunyangbeli="",idbarang1="",idbarang2="";
    int jumlah=0,jumlah2=0;
    ArrayList<ClassNota> listClassNota = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alamat);
        nama=findViewById(R.id.etNamaPenerima);
        nohp=findViewById(R.id.etNomorHp);
        kota=findViewById(R.id.etKota);
        kode=findViewById(R.id.etKode);
        alamat=findViewById(R.id.etAlamat);
        pembayaran = findViewById(R.id.sppembayaran);
        konfirmasi=findViewById(R.id.btnKonfirmasi);

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



        Intent i = getIntent();
        if (i.hasExtra("idbarang1")) {
            akunyangbeli = i.getStringExtra("akunyangbeli");
            idbarang1 = i.getStringExtra("idbarang1");
            idbarang2 = i.getStringExtra("idbarang2");
            jumlah = i.getIntExtra("jumlah",0);
            jumlah2 = i.getIntExtra("jumlah2",0);
        }
        else{
            akunyangbeli = i.getStringExtra("akunyangbeli");
            idbarang1 = i.getStringExtra("idbarang");
            jumlah = i.getIntExtra("jumlah",0);

            Toast.makeText(this, akunyangbeli+" "+idbarang1+" "+jumlah, Toast.LENGTH_SHORT).show();
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

    public void toDetailPengiriman(View view) {
        String strnama = nama.getText().toString();
        String strnohp = nohp.getText().toString();
        String stralamat = alamat.getText().toString();
        String strkota = kota.getText().toString();
        String strkode = kode.getText().toString();

        if(strnama.equals("")||strnohp.equals("")||stralamat.equals("")||strkota.equals("")||strkode.equals("")){
            Toast.makeText(this, "Isi semua field terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
        else{
            FirebaseDatabase.getInstance().getReference().child("ClassNota").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        ClassNota semua_Class_Nota =new ClassNota();
                        semua_Class_Nota.setIdnota((ds.child("idnota").getValue().toString()));
                        semua_Class_Nota.setNamatoko((ds.child("idnota").getValue().toString()));
                        semua_Class_Nota.setIdnota((ds.child("idnota").getValue().toString()));
                        semua_Class_Nota.setIdnota((ds.child("idnota").getValue().toString()));
                        semua_Class_Nota.setIdnota((ds.child("idnota").getValue().toString()));
                        semua_Class_Nota.setIdnota((ds.child("idnota").getValue().toString()));
                        semua_Class_Nota.setIdnota((ds.child("idnota").getValue().toString()));
                        semua_Class_Nota.setIdnota((ds.child("idnota").getValue().toString()));
                        semua_Class_Nota.setIdnota((ds.child("idnota").getValue().toString()));


                       // listClassBarang.add(semua_Class_barang);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            Intent i = new Intent(DetailAlamat.this,NotaActivity.class);
            startActivity(i);
        }


    }
}
