package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    BottomNavigationView bottomNavHistory;
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassNota> listClassNota = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassBarang semua_Class_Barang =new ClassBarang();
                    semua_Class_Barang.setIdbarang((ds.child("idbarang").getValue().toString()));
                    semua_Class_Barang.setNamabarang((ds.child("namabarang").getValue().toString()));
                    listClassBarang.add(semua_Class_Barang);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("ClassNota").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassNota semua_Class_Nota =new ClassNota();
                    semua_Class_Nota.setIdnota((ds.child("idnota").getValue().toString()));
                    semua_Class_Nota.setNamatoko((ds.child("namatoko").getValue().toString()));
                    semua_Class_Nota.setIdbarang((ds.child("idbarang").getValue().toString()));
                    semua_Class_Nota.setNamauser(ds.child("namauser").getValue().toString());
                    semua_Class_Nota.setAlamat((ds.child("alamat").getValue().toString()));
                    semua_Class_Nota.setPembayaran((ds.child("pembayaran").getValue().toString()));
                    semua_Class_Nota.setJenispengiriman((ds.child("jenispengiriman").getValue().toString()));
                    semua_Class_Nota.setHargabarang(Integer.parseInt(ds.child("hargabarang").getValue().toString()));
                    semua_Class_Nota.setJumlahbarang(Integer.parseInt(ds.child("jumlahbarang").getValue().toString()));
                    semua_Class_Nota.setHargapengiriman(Integer.parseInt(ds.child("hargapengiriman").getValue().toString()));
                    semua_Class_Nota.setTotal(Integer.parseInt(ds.child("total").getValue().toString()));
                    semua_Class_Nota.setPosisi(Integer.parseInt(ds.child("posisi").getValue().toString()));
                    listClassNota.add(semua_Class_Nota);
                }
                changeFragment(new FragmentDitunda());
                bottomNavHistory = findViewById(R.id.bottomNavHistory);
                bottomNavHistory.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        if(menuItem.getItemId( )== R.id.itemDitunda){
                            changeFragment(new FragmentDitunda());
                        }else if(menuItem.getItemId( )== R.id.itemMenunggu){
                            changeFragment(new FragmentMenunggu());
                        }else if(menuItem.getItemId( )== R.id.itemTerkirim){
                            changeFragment(new FragmentTerkirim());
                        }else if(menuItem.getItemId( )== R.id.itemBatal){
                            changeFragment(new FragmentBatal());
                        }
                        return true;
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            Intent i = new Intent(HistoryActivity.this,Home.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(Fragment f){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.containerHistory, f);
        ft.commit();
    }
}