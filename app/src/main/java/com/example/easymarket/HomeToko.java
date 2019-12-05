package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeToko extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ArrayList<User> listUser ;
    ArrayList<Barang> listBarang;
    ArrayList<Toko> listToko ;
    ArrayList<ClassWishlist> listWishlist;
    ArrayList<ClassRequestLelang> listRequestLelang = new ArrayList<>();
    String tokologin="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_toko);

        Intent i = getIntent();
        listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
        listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
        listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
        listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
        listRequestLelang= (ArrayList<ClassRequestLelang>) i.getSerializableExtra("listRequestLelang");

        changeFragment(new FragmentListBarang(),listBarang);
        bottomNavigationView=findViewById(R.id.btmNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId( )== R.id.navListBarang){
                    changeFragment(new FragmentListBarang(),listBarang);
                }
                else if(menuItem.getItemId( )== R.id.navTambah){
                    changeFragment(new FragmentTambahBarang(),listBarang);
                }
                else if(menuItem.getItemId( )== R.id.navInbox){
                    changeFragment(new FragmentInbox(),listBarang);
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
        menu.getItem(4).setVisible(false);
        menu.getItem(5).setVisible(false);
        menu.getItem(6).setVisible(false);
        menu.getItem(7).setVisible(false);
        menu.getItem(8).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itemLogout){
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(HomeToko.this,Login.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(Fragment f,ArrayList<Barang>listBarang){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("listBarang",listBarang);
        f.setArguments(bundle);
        ft.replace(R.id.fragmentContainer, f);
        ft.commit();
    }
}

