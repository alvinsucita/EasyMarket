package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class WishList extends AppCompatActivity {
    ArrayList<ClassUser> listClassUser = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassToko> listClassToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    ArrayList<ClassWishlist> filterWishlist = new ArrayList<>();
    ArrayList<ClassNota> listNota = new ArrayList<>();
    TextView total;
    Button beli;
    String yanglogin="";
    RecyclerView rv;
    AdapterWishlist adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        total=findViewById(R.id.tvTotal);
        beli=findViewById(R.id.btnBeli);
        rv=findViewById(R.id.rvWishlist);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setColor(Color.WHITE);
        drawable4.setShape(GradientDrawable.OVAL);
        drawable4.setStroke(5, Color.BLACK);
        beli.setBackground(drawable4);

        total.setText("Total Harga : ");

        Intent i = getIntent();
        listClassUser = (ArrayList<ClassUser>) i.getSerializableExtra("listClassUser");
        listClassToko = (ArrayList<ClassToko>) i.getSerializableExtra("listClassToko");
        listClassBarang = (ArrayList<ClassBarang>) i.getSerializableExtra("listClassBarang");
        listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");


        for (int j = 0; j < listClassUser.size(); j++) {
//            if(listClassUser.get(j).aktif.equals("1")){
//                yanglogin=listClassUser.get(j).nama;
//            }
        }
        for (int j = 0; j < listWishlist.size(); j++) {
            if(listWishlist.get(j).yangbeli.equals(yanglogin)){
                filterWishlist.add(new ClassWishlist(listWishlist.get(j).namabarang,listWishlist.get(j).hargabarang,yanglogin));
            }
        };

        adapter=new AdapterWishlist(filterWishlist, listClassBarang);
        adapter.setClick(new AdapterWishlist.onClickCallback() {
            @Override
            public void onClickCheckbox(ClassBarang b) {
                total.setText(b.harga+"");
            }
        });
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void toDetailAlamat(View view) {
        Intent i = new Intent(WishList.this, DetailAlamat.class);
        startActivity(i);
    }
}