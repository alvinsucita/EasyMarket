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

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    BottomNavigationView bottomNavHistory;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    ArrayList<ClassRequestLelang> listRequestLelang = new ArrayList<>();
    int indeks=0;
    String aktif="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        changeFragment(new FragmentDitunda(),listBarang,listUser);
        bottomNavHistory = findViewById(R.id.bottomNavHistory);
        bottomNavHistory.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId( )== R.id.itemDitunda){
                    changeFragment(new FragmentDitunda(),listBarang,listUser);
                }else if(menuItem.getItemId( )== R.id.itemMenunggu){
                    changeFragment(new FragmentMenunggu(),listBarang,listUser);
                }else if(menuItem.getItemId( )== R.id.itemTerkirim){
                    changeFragment(new FragmentTerkirim(),listBarang,listUser);
                }else if(menuItem.getItemId( )== R.id.itemBatal){
                    changeFragment(new FragmentBatal(),listBarang,listUser);
                }
                return true;
            }
        });

        Intent i = getIntent();
        listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
        listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
        listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
        listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
        indeks=i.getIntExtra("indeks",indeks);
        if(i.hasExtra("adayanglogin")){
            aktif=i.getStringExtra("adayanglogin");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            Intent i = new Intent(HistoryActivity.this,Home.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listToko", listToko);
            i.putExtra("listBarang", listBarang);
            i.putExtra("listWishlist", listWishlist);
            if(aktif.equals("1")){
                i.putExtra("adayanglogin","1");
            }
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(Fragment f, ArrayList<Barang> listBarang, ArrayList<User>listUser){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("listBarang",listBarang);
        bundle.putSerializable("listUser",listUser);
        f.setArguments(bundle);
        ft.replace(R.id.containerHistory, f);
        ft.commit();
    }

    public Intent putextra(Intent i){
        i.putExtra("listUser", listUser);
        i.putExtra("listWishlist", listWishlist);
        i.putExtra("listToko", listToko);
        i.putExtra("listBarang", listBarang);
        i.putExtra("listRequestLelang", listRequestLelang);
        return i;
    }
}
