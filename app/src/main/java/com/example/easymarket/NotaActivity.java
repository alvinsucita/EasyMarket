package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotaActivity extends AppCompatActivity implements NotaProdukFragment.OnFragmentInteractionListener{

    BottomNavigationView bottomNavigationNota;
    Button konfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        konfirmasi = findViewById(R.id.btkonfirmasinota);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setCornerRadius(100);
        drawable4.setColor(Color.BLACK);
        konfirmasi.setBackground(drawable4);

        changeFragment(new NotaProdukFragment(),"");
        bottomNavigationNota = findViewById(R.id.BottomNavNota);
        bottomNavigationNota.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.produk){
                    changeFragment(new NotaProdukFragment(),"");
                }else if(menuItem.getItemId()==R.id.pengiriman){
                    changeFragment(new NotaPengirimanFragment(),"");
                }else if(menuItem.getItemId()==R.id.pembayaran){
                    changeFragment(new NotaPembayaranFragment(),"");
                }
                return true;
            }
        });


    }

    public void changeFragment(Fragment f, String data){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.FragmentNota, f);
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void konfirmasi(View view) {
        Intent i = new Intent(NotaActivity.this,HistoryActivity.class);
        startActivity(i);
    }
}
