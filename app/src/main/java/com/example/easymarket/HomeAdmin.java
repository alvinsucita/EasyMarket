package com.example.easymarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeAdmin extends AppCompatActivity {

    ArrayList<ClassUser> listClassUser = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassToko> listClassToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        Intent i = getIntent();
        if (i.hasExtra("listClassUser")) {
            listClassUser = (ArrayList<ClassUser>) i.getSerializableExtra("listClassUser");
            listClassToko = (ArrayList<ClassToko>) i.getSerializableExtra("listClassToko");
            listClassBarang = (ArrayList<ClassBarang>) i.getSerializableExtra("listClassBarang");
            listWishlist = (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
        }
    }

    public Intent putextra(Intent i){
        i.putExtra("listClassUser", listClassUser);
        i.putExtra("listWishlist", listWishlist);
        i.putExtra("listClassToko", listClassToko);
        i.putExtra("listClassBarang", listClassBarang);
        return i;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menuadmin,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.addevent){
            Intent i = new Intent(HomeAdmin.this,InputEvent.class);
            i = putextra(i);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.manageuser){
            Intent i = new Intent(HomeAdmin.this,ManageUsers.class);
            i = putextra(i);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.managetoko){
            Intent i = new Intent(HomeAdmin.this,MasterToko.class);
            i = putextra(i);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.managelelang){
            Intent i = new Intent(HomeAdmin.this,MasterLelang.class);
            i = putextra(i);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.logoutadmin){
            Intent i = new Intent(HomeAdmin.this,Login.class);
            i = putextra(i);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
