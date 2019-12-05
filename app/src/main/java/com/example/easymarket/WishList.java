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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class WishList extends AppCompatActivity {
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
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
        listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
        listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
        listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
        listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");


        for (int j = 0; j < listUser.size(); j++) {
//            if(listUser.get(j).aktif.equals("1")){
//                yanglogin=listUser.get(j).nama;
//            }
        }
        for (int j = 0; j < listWishlist.size(); j++) {
            if(listWishlist.get(j).yangbeli.equals(yanglogin)){
                filterWishlist.add(new ClassWishlist(listWishlist.get(j).namabarang,listWishlist.get(j).hargabarang,yanglogin));
            }
        };

        adapter=new AdapterWishlist(filterWishlist,listBarang);
        adapter.setClick(new AdapterWishlist.onClickCallback() {
            @Override
            public void onClickCheckbox(Barang b) {
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