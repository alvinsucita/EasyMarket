package com.example.easymarket;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class InfoBarang extends AppCompatActivity {
    BottomNavigationView bottomNavBarang;
    TextView nama,hargabarang;
    Button share,chat,add;
    ArrayList<ClassUser> listClassUser = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    DatabaseReference databaseReference_barang,databaseReference_user,databaseReference_wishlist;
    String aktif="0";
    int indeks=0;
    ImageView foto;
    String emailtoko="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_barang);

        nama=findViewById(R.id.tvNamaBarang);
        hargabarang=findViewById(R.id.tvHargaBarang);
        add=findViewById(R.id.btnAddToWishlist);
        chat=findViewById(R.id.btnPersonalChat);
        share=findViewById(R.id.btnShare);
        foto=findViewById(R.id.ivFoto);
        nama.setText("");
        hargabarang.setText("");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.BLACK);
        share.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setColor(Color.BLACK);
        chat.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setColor(Color.BLACK);
        add.setBackground(drawable3);

        Intent i = getIntent();
        indeks=i.getIntExtra("indeks",indeks);

        databaseReference_barang = FirebaseDatabase.getInstance().getReference().child("ClassBarang");
        databaseReference_barang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassBarang semua_Class_barang =new ClassBarang();
                    semua_Class_barang.setDeskripsi(ds.child("deskripsi").getValue().toString());
                    semua_Class_barang.setDibeli(Integer.parseInt(ds.child("dibeli").getValue().toString()));
                    semua_Class_barang.setDilihat(Integer.parseInt(ds.child("dilihat").getValue().toString()));
                    semua_Class_barang.setHarga(Integer.parseInt(ds.child("harga").getValue().toString()));
                    semua_Class_barang.setIdbarang(ds.child("idbarang").getValue().toString());
                    semua_Class_barang.setKategori(ds.child("kategori").getValue().toString());
                    semua_Class_barang.setLikes(Integer.parseInt(ds.child("likes").getValue().toString()));
                    semua_Class_barang.setNamabarang(ds.child("namabarang").getValue().toString());
                    semua_Class_barang.setNamatoko(ds.child("namatoko").getValue().toString());
                    semua_Class_barang.setStok(Integer.parseInt(ds.child("stok").getValue().toString()));
                    listClassBarang.add(semua_Class_barang);
                }
                String hargaasli = String.format("%,d", listClassBarang.get(indeks).harga);
                nama.setText(listClassBarang.get(indeks).namabarang);
                hargabarang.setText("Rp. "+hargaasli);
                FirebaseStorage.getInstance().getReference().child("GambarBarang").child(listClassBarang.get(indeks).idbarang).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(getApplicationContext()).load(uri).into(foto);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("ClassBarang");
        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("namabarang").getValue().toString().equals(nama.getText().toString())){
                        ClassBarang updatebarang = new ClassBarang();
                        updatebarang.setKategori(ds.child("kategori").getValue().toString());
                        updatebarang.setDeskripsi(ds.child("deskripsi").getValue().toString());
                        updatebarang.setDibeli(Integer.parseInt(ds.child("dibeli").getValue().toString()));
                        updatebarang.setDilihat(Integer.parseInt(ds.child("dilihat").getValue().toString())+1);
                        updatebarang.setHarga(Integer.parseInt(ds.child("harga").getValue().toString()));
                        updatebarang.setIdbarang(ds.child("idbarang").getValue().toString());
                        updatebarang.setLikes(Integer.parseInt(ds.child("likes").getValue().toString()));
                        updatebarang.setNamabarang(ds.child("namabarang").getValue().toString());
                        updatebarang.setNamatoko(ds.child("namatoko").getValue().toString());
                        updatebarang.setStok(Integer.parseInt(ds.child("stok").getValue().toString()));
                        databaseReference2.child(ds.getKey()).setValue(updatebarang);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference_wishlist = FirebaseDatabase.getInstance().getReference().child("ClassWishlist");
        databaseReference_wishlist.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassWishlist semua_Class_wishlist =new ClassWishlist();
                    semua_Class_wishlist.setIdbarang(ds.child("idbarang").getValue().toString());
                    semua_Class_wishlist.setEmailpembeli(ds.child("emailpembeli").getValue().toString());
                    listWishlist.add(semua_Class_wishlist);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        changeFragment(new FragmentInfoBarang(), listClassBarang, listClassUser);
        bottomNavBarang=findViewById(R.id.bottomNavBarang);
        bottomNavBarang.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId( )== R.id.infobarang){
                    changeFragment(new FragmentInfoBarang(), listClassBarang, listClassUser);
                }
                else if(menuItem.getItemId( )== R.id.commentbarang){
                    changeFragment(new FragmentCommentBarang(getApplicationContext()), listClassBarang, listClassUser);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            Intent i = new Intent(InfoBarang.this,Home.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void toChat(View view) {
        emailtoko=listClassBarang.get(indeks).namatoko;
        Intent i = new Intent(InfoBarang.this,ChatRoom.class);
        i.putExtra("emailtoko",emailtoko);
        startActivity(i);
    }

    public void addwishlist(View view) {
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassUser");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                            cek=false;
                        }
                    }
                    if(cek){
                        Toast.makeText(getApplicationContext(), "Login terlebih dahulu", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        databaseReference_user = FirebaseDatabase.getInstance().getReference().child("ClassUser");
                        databaseReference_user.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Boolean cek = true;
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                        String yangbeli = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                                        int ctr=0;
                                        String stridbarang="";

                                        for (int i = 0; i < listClassBarang.size(); i++) {
                                            if(listClassBarang.get(i).namabarang.equals(nama.getText().toString())){
                                                stridbarang=listClassBarang.get(i).idbarang;
                                            }
                                        }

                                        for (int i = 0; i < listWishlist.size(); i++) {
                                            if(listWishlist.get(i).idbarang.equals(stridbarang)&&listWishlist.get(i).emailpembeli.equals(yangbeli)){
                                                ctr++;
                                            }
                                        }

                                        if(ctr==0){
                                            ClassWishlist wishlistbaru=new ClassWishlist(stridbarang,yangbeli);
                                            databaseReference_wishlist = FirebaseDatabase.getInstance().getReference().child("ClassWishlist");
                                            String key=databaseReference_wishlist.push().getKey();
                                            databaseReference_wishlist.child(key).setValue(wishlistbaru).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(getApplicationContext(), "Barang berhasil ditambahkan ke wishlist", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                        else{
                                            Toast.makeText(InfoBarang.this, "Barang ini sudah ada di wishlist anda", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void share(View view) {
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassUser");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                            cek=false;
                        }
                    }
                    if(cek){
                        Toast.makeText(InfoBarang.this, "Login terlebih dahulu", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, listClassBarang.get(indeks).namabarang+"\n"+ listClassBarang.get(indeks).harga );
                        sendIntent.setType("text/plain");
                        startActivity(Intent.createChooser(sendIntent, "Share"));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void changeFragment(Fragment f, ArrayList<ClassBarang> listClassBarang, ArrayList<ClassUser> listClassUser){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.containerBarang, f);
        ft.commit();
    }

    public void toVideo(View view) {
        Intent i = new Intent(InfoBarang.this,PreviewActivity.class);
        i.putExtra("nama",nama.getText().toString());
        startActivity(i);
    }
}