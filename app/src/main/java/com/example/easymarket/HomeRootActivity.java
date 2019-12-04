package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeRootActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_root);

        changeFragment(new FragmentHome());
        bottomNavigationView=findViewById(R.id.btmNavRootHome);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId( )== R.id.itemHome){
                    changeFragment(new FragmentHome());
                }
                else if(menuItem.getItemId( )== R.id.itemEvent){
                    changeFragment(new FragmentEvent());
                }
                else if(menuItem.getItemId( )== R.id.itemLelang){
                    changeFragment(new FragmentLelang());
                }
                else if(menuItem.getItemId( )== R.id.itemHistory){
                    changeFragment(new FragmentHistory());
                }
                return true;
            }
        });
    }

    public void changeFragment(Fragment f){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, f);
        ft.commit();
    }

}
