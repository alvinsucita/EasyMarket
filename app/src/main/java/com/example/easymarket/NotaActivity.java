package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotaActivity extends AppCompatActivity implements NotaProdukFragment.OnFragmentInteractionListener{

    BottomNavigationView bottomNavigationNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);
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

        changeFragment(new NotaProdukFragment(),"");
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
}
