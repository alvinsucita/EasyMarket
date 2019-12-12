package com.example.easymarket;

import androidx.annotation.NonNull;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WishList extends AppCompatActivity {
    ArrayList<ClassUser> listClassUser = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassToko> listClassToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlistku = new ArrayList<>();
    TextView total;
    Button beli;
    RecyclerView rv;
    AdapterWishlist adapter;
    DatabaseReference databaseReference_barang,databaseReference_user,databaseReference_wishlist;

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

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setColor(Color.BLACK);
        beli.setBackground(drawable3);

        total.setText("Total Harga : ");

        databaseReference_wishlist = FirebaseDatabase.getInstance().getReference().child("ClassWishlist");
        databaseReference_wishlist.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("emailpembeli").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        ClassWishlist wishlistku =new ClassWishlist();
                        wishlistku.setIdbarang(ds.child("idbarang").getValue().toString());
                        wishlistku.setEmailpembeli(ds.child("emailpembeli").getValue().toString());
                        listWishlistku.add(wishlistku);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference_barang = FirebaseDatabase.getInstance().getReference().child("ClassBarang");
        databaseReference_barang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    for (int i = 0; i < listWishlistku.size(); i++) {
                        if(listWishlistku.get(i).idbarang.equals(ds.child("idbarang").getValue().toString())){
                            ClassBarang barangwishlistku =new ClassBarang();
                            barangwishlistku.setDeskripsi(ds.child("deskripsi").getValue().toString());
                            barangwishlistku.setDibeli(Integer.parseInt(ds.child("dibeli").getValue().toString()));
                            barangwishlistku.setDilihat(Integer.parseInt(ds.child("dilihat").getValue().toString()));
                            barangwishlistku.setHarga(Integer.parseInt(ds.child("harga").getValue().toString()));
                            barangwishlistku.setIdbarang(ds.child("idbarang").getValue().toString());
                            barangwishlistku.setKategori(ds.child("kategori").getValue().toString());
                            barangwishlistku.setLikes(Integer.parseInt(ds.child("likes").getValue().toString()));
                            barangwishlistku.setNamabarang(ds.child("namabarang").getValue().toString());
                            barangwishlistku.setNamatoko(ds.child("namatoko").getValue().toString());
                            barangwishlistku.setStok(Integer.parseInt(ds.child("stok").getValue().toString()));
                            listClassBarang.add(barangwishlistku);
                        }
                    }
                }
                adapter=new AdapterWishlist(listClassBarang);
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
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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