package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InfoPengirimanActivity extends AppCompatActivity {

    BottomNavigationView bottomView;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pengiriman);

        fragment = new FragmentTerkirim();
        openFragment(fragment);
        bottomView = findViewById(R.id.navInfoPengirimanActivity);
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.item_Terkirim){
                    fragment = new FragmentTerkirim();
                }
                if(menuItem.getItemId() == R.id.item_batal){
                    fragment = new FragmentBatal();
                }
                if(menuItem.getItemId() == R.id.item_Belum_bayar){
                    fragment = new FragmentBelumBayar();
                }
                if(menuItem.getItemId() == R.id.item_request){
                    fragment = new FragmentBelumBayar();
                }
                openFragment(fragment);
                return true;
            }
        });


    }

    public void openFragment(Fragment fr){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle temp = new Bundle();
        fr.setArguments(temp);
        ft.replace(R.id.ContainerFrameActivityInfo,fr);
        ft.commit();
    }

}
