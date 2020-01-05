package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeToko extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ArrayList<ClassUser> listClassUser;
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassToko> listClassToko;
    ArrayList<ClassWishlist> listWishlist;
    ArrayList<ClassRequestLelang> listRequestLelang = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_toko);

        FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("namatoko").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        ClassBarang semua_Class_barang =new ClassBarang();
                        semua_Class_barang.setDeskripsi(ds.child("deskripsi").getValue().toString());
                        semua_Class_barang.setDibeli(Integer.parseInt(ds.child("dibeli").getValue().toString()));
                        semua_Class_barang.setDilihat(Integer.parseInt(ds.child("dilihat").getValue().toString()));
                        semua_Class_barang.setHarga(Integer.parseInt(ds.child("harga").getValue().toString()));
                        semua_Class_barang.setIdbarang(ds.child("idbarang").getValue().toString());
                        semua_Class_barang.setKategori(ds.child("kategori").getValue().toString());
                        semua_Class_barang.setLikes(Integer.parseInt(ds.child("likes").getValue().toString()));
                        semua_Class_barang.setNamabarang(ds.child("namabarang").getValue().toString());
                        semua_Class_barang.setNamatoko(ds.child("namatoko").getValue().toString());
                        semua_Class_barang.setStok(Integer.parseInt(ds.child("stok").getValue().toString()));
                        listClassBarang.add(semua_Class_barang);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        changeFragment(new FragmentListBarang(), listClassBarang);
        bottomNavigationView=findViewById(R.id.btmNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId( )== R.id.navListBarang){
                    changeFragment(new FragmentListBarang(), listClassBarang);
                }
                else if(menuItem.getItemId( )== R.id.navTambah){
                    changeFragment(new FragmentTambahBarang(), listClassBarang);
                }
                else if(menuItem.getItemId( )== R.id.navInbox){
                    changeFragment(new FragmentInbox(), listClassBarang);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        menu.getItem(0).setVisible(false);
        menu.getItem(1).setVisible(false);
        menu.getItem(2).setVisible(false);
        menu.getItem(3).setVisible(false);
        menu.getItem(4).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itemLogout){
            FirebaseAuth.getInstance().signOut();
            FirebaseAuth.getInstance().signInWithEmailAndPassword("guest@guest.com","guest123");
            Intent i = new Intent(HomeToko.this,Login.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(Fragment f,ArrayList<ClassBarang> listClassBarang){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("listClassBarang", listClassBarang);
        f.setArguments(bundle);
        ft.replace(R.id.fragmentContainer, f);
        ft.commit();
    }
}

