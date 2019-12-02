package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class HomeAdmin extends AppCompatActivity {

    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        Intent i = getIntent();
        if (i.hasExtra("listUser")) {
            listUser = (ArrayList<User>) i.getSerializableExtra("listUser");
            listToko = (ArrayList<Toko>) i.getSerializableExtra("listToko");
            listBarang = (ArrayList<Barang>) i.getSerializableExtra("listBarang");
            listWishlist = (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
        }
    }

    public Intent putextra(Intent i){
        i.putExtra("listUser", listUser);
        i.putExtra("listWishlist", listWishlist);
        i.putExtra("listToko", listToko);
        i.putExtra("listBarang", listBarang);
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
        else if(item.getItemId()==R.id.changelog){
            Intent i = new Intent(HomeAdmin.this,MasterChangelog.class);
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
        else if(item.getItemId()==R.id.managetickets){
            Intent i = new Intent(HomeAdmin.this,ManageTickets.class);
            i = putextra(i);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.managelelang){
            Intent i = new Intent(HomeAdmin.this,MasterLelang.class);
            i = putextra(i);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.managetrans){
            Intent i = new Intent(HomeAdmin.this,MasterTrans.class);
            i = putextra(i);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.managekas){
            Intent i = new Intent(HomeAdmin.this,MasterKas.class);
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
