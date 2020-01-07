package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class InfoBarangLelang extends AppCompatActivity {

    TextView deskripsi,nama,hargaasli,hargaawal,dilihat;
    EditText hargabid;
    Button share, bid;
    ImageView foto;
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassBarang> filterBarang = new ArrayList<>();
    ArrayList<ClassLelang> listClassLelang = new ArrayList<>();
    int indeks=0;
    int indekslelang=0;

    String yangbid="",idbarang="";
    int hargatertinggi=0,hargayangdimasukkan=0,hargaminim=0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            Intent i = new Intent(InfoBarangLelang.this,Home.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_barang_lelang);

        deskripsi = findViewById(R.id.tvIsiDeskripsiLelang);
        share = findViewById(R.id.btnShareLelang);
        bid = findViewById(R.id.btnBid);
        hargaasli=findViewById(R.id.tvHargaBarangLelang);
        hargaawal=findViewById(R.id.tvHargaLelang);
        dilihat=findViewById(R.id.tvBarangDilihatLelang);
        nama=findViewById(R.id.tvNamaBarangLelang);
        foto=findViewById(R.id.ivFotoLelang);
        hargabid=findViewById(R.id.etNominalBid);


        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.BLACK);
        share.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setColor(Color.BLACK);
        bid.setBackground(drawable2);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setColor(Color.WHITE);
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setStroke(5, Color.BLACK);
        drawable4.setCornerRadius(50);
        deskripsi.setBackground(drawable4);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent i = getIntent();
        i.getIntExtra("indeks",0);

        FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassBarang semua_Class_barang =new ClassBarang();
                    semua_Class_barang.setDeskripsi(ds.child("deskripsi").getValue().toString());
                    semua_Class_barang.setDibeli(Integer.parseInt(ds.child("dibeli").getValue().toString()));
                    semua_Class_barang.setDilihat(Integer.parseInt(ds.child("dilihat").getValue().toString()));
                    semua_Class_barang.setHarga(Integer.parseInt(ds.child("harga").getValue().toString()));
                    semua_Class_barang.setIdbarang(ds.child("idbarang").getValue().toString());
                    semua_Class_barang.setKategori(ds.child("kategori").getValue().toString());
                    semua_Class_barang.setLikes(Integer.parseInt(ds.child("likes").getValue().toString()));
                    semua_Class_barang.setNamabarang(ds.child("namabarang").getValue().toString());
                    semua_Class_barang.setToko(ds.child("toko").getValue().toString());
                    semua_Class_barang.setStok(Integer.parseInt(ds.child("stok").getValue().toString()));
                    listClassBarang.add(semua_Class_barang);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("ClassLelang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassLelang semua_Class_lelang =new ClassLelang();
                    semua_Class_lelang.setIdbarang(ds.child("idbarang").getValue().toString());
                    semua_Class_lelang.setHarganormal(Integer.parseInt(ds.child("harganormal").getValue().toString()));
                    semua_Class_lelang.setHargatertinggi(Integer.parseInt(ds.child("hargatertinggi").getValue().toString()));
                    semua_Class_lelang.setHargaawal(Integer.parseInt(ds.child("hargaawal").getValue().toString()));
                    semua_Class_lelang.setNamabidder(ds.child("namabidder").getValue().toString());
                    listClassLelang.add(semua_Class_lelang);
                }

                for (int j = 0; j < listClassLelang.size(); j++) {
                    if(listClassLelang.get(j).idbarang.equals(listClassBarang.get(indeks).idbarang)){
                        String hargaaslii = String.format("%,d", listClassLelang.get(j).hargaawal);
                        hargaawal.setText("Mulai dari : Rp. "+hargaaslii);
                        yangbid= FirebaseAuth.getInstance().getCurrentUser().getEmail();
                        hargatertinggi=listClassLelang.get(j).hargatertinggi;
                        hargaminim=listClassLelang.get(j).hargaawal;
                    }
                }


                nama.setText(listClassBarang.get(indeks).namabarang);
                String hargaaslii = String.format("%,d", listClassBarang.get(indeks).harga);
                hargaasli.setText("Harga asli : "+"Rp. "+hargaaslii);
                FirebaseStorage.getInstance().getReference().child("GambarBarang").child(listClassBarang.get(indeks).idbarang).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(getApplicationContext()).load(uri).into(foto);
                    }
                });
                deskripsi.setText(listClassBarang.get(indeks).deskripsi);
                dilihat.setText("Dilihat : "+listClassBarang.get(indeks).dilihat+"kali");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void share(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, listClassBarang.get(indeks).namabarang+"\n"+ listClassBarang.get(indeks).harga );
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share"));
    }

    public void bid(View view) {
        hargayangdimasukkan=Integer.parseInt(hargabid.getText().toString());
        if(hargayangdimasukkan>hargaminim){
            if(hargayangdimasukkan>hargatertinggi){
                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassLelang");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Boolean cek = true;
                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                            if(ds.child("idbarang").getValue().toString().equals(listClassBarang.get(indeks).idbarang)){
                                ClassLelang updatelelang = new ClassLelang();
                                updatelelang.setIdbarang(ds.child("idbarang").getValue().toString());
                                updatelelang.setHargaawal(Integer.parseInt(ds.child("hargaawal").getValue().toString()));
                                updatelelang.setHargatertinggi(hargayangdimasukkan);
                                updatelelang.setHarganormal(Integer.parseInt(ds.child("harganormal").getValue().toString()));
                                updatelelang.setNamabidder(yangbid);
                                databaseReference.child(ds.getKey()).setValue(updatelelang).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(InfoBarangLelang.this, "Bid anda berhasil dimasukkan, tunggu konfirmasi selanjutnya", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        hargabid.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            else if(hargayangdimasukkan<hargatertinggi){
                Toast.makeText(this, "Bid anda berhasil dimasukkan, tunggu konfirmasi selanjutnya", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Bid yang dimasukkan harus lebih besar dari harga minimal", Toast.LENGTH_SHORT).show();
        }


    }
}
