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
    ArrayList<ClassUser> listClassUser = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassToko> listClassToko = new ArrayList<>();
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

        changeFragment(new FragmentDitunda(), listClassBarang, listClassUser);
        bottomNavHistory = findViewById(R.id.bottomNavHistory);
        bottomNavHistory.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId( )== R.id.itemDitunda){
                    changeFragment(new FragmentDitunda(), listClassBarang, listClassUser);
                }else if(menuItem.getItemId( )== R.id.itemMenunggu){
                    changeFragment(new FragmentMenunggu(), listClassBarang, listClassUser);
                }else if(menuItem.getItemId( )== R.id.itemTerkirim){
                    changeFragment(new FragmentTerkirim(), listClassBarang, listClassUser);
                }else if(menuItem.getItemId( )== R.id.itemBatal){
                    changeFragment(new FragmentBatal(), listClassBarang, listClassUser);
                }
                return true;
            }
        });

        Intent i = getIntent();
        listClassUser = (ArrayList<ClassUser>) i.getSerializableExtra("listClassUser");
        listClassToko = (ArrayList<ClassToko>) i.getSerializableExtra("listClassToko");
        listClassBarang = (ArrayList<ClassBarang>) i.getSerializableExtra("listClassBarang");
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
            i.putExtra("listClassUser", listClassUser);
            i.putExtra("listClassToko", listClassToko);
            i.putExtra("listClassBarang", listClassBarang);
            i.putExtra("listWishlist", listWishlist);
            if(aktif.equals("1")){
                i.putExtra("adayanglogin","1");
            }
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(Fragment f, ArrayList<ClassBarang> listClassBarang, ArrayList<ClassUser> listClassUser){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("listClassBarang", listClassBarang);
        bundle.putSerializable("listClassUser", listClassUser);
        f.setArguments(bundle);
        ft.replace(R.id.containerHistory, f);
        ft.commit();
    }

    public Intent putextra(Intent i){
        i.putExtra("listClassUser", listClassUser);
        i.putExtra("listWishlist", listWishlist);
        i.putExtra("listClassToko", listClassToko);
        i.putExtra("listClassBarang", listClassBarang);
        i.putExtra("listRequestLelang", listRequestLelang);
        return i;
    }
}
