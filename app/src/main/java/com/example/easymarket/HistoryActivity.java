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
    ArrayList<ClassUser> listClassUser = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassNota> listClassNota = new ArrayList<>();
    ArrayList<ClassToko> listClassToko = new ArrayList<>();
    ArrayList<ClassRequestLelang> listRequestLelang = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        changeFragment(new FragmentMenunggu(),listClassNota);
        bottomNavHistory = findViewById(R.id.bottomNavHistory);
        bottomNavHistory.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId( )== R.id.itemDitunda){
                    changeFragment(new FragmentDitunda(),listClassNota);
                }else if(menuItem.getItemId( )== R.id.itemMenunggu){
                    changeFragment(new FragmentMenunggu(),listClassNota);
                }else if(menuItem.getItemId( )== R.id.itemTerkirim){
                    changeFragment(new FragmentTerkirim(),listClassNota);
                }else if(menuItem.getItemId( )== R.id.itemBatal){
                    changeFragment(new FragmentBatal(),listClassNota);
                }
                return true;
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
                    listClassNota.add(semua_Class_Nota);
                }
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

    public void changeFragment(Fragment f,ArrayList<ClassNota> listClassNota){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("listClassNota", listClassNota);
        f.setArguments(bundle);
        ft.replace(R.id.containerHistory, f);
        ft.commit();
    }
}