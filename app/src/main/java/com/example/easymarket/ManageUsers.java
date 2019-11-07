package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class ManageUsers extends AppCompatActivity {

    ListView listView;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();

    ArrayList<String> test = new ArrayList<>();
    String[] user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        if (i.hasExtra("listUser")) {
            listUser = (ArrayList<User>) i.getSerializableExtra("listUser");
            listToko = (ArrayList<Toko>) i.getSerializableExtra("listToko");
            listBarang = (ArrayList<Barang>) i.getSerializableExtra("listBarang");
            listWishlist = (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
            for(int a = 0; a<listUser.size(); a++){
                test.add(listUser.get(a).getNama());
            }
            user = new String[listUser.size()];
            user = test.toArray(user);
        }

        listView = findViewById(R.id.lvusers);
        AdapterMenuAdmin ama = new AdapterMenuAdmin(this, user, user);
        listView.setAdapter(ama);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
