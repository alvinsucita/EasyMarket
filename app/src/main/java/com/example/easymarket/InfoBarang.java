package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InfoBarang extends AppCompatActivity {
    BottomNavigationView bottomNavBarang;
    TextView nama,hargabarang;
    Button share,chat,add;
    ArrayList<ClassUser> listClassUser = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    ArrayList<ClassToko> listClassToko = new ArrayList<>();
    DatabaseReference databaseReference;
    int jumlah= 0;
    String aktif="0";
    int indeks=0;
    ImageView foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_barang);

        nama=findViewById(R.id.tvNamaBarang);
        hargabarang=findViewById(R.id.tvHargaBarang);
        add=findViewById(R.id.btnAddToWishlist);
        chat=findViewById(R.id.btnPersonalChat);
        share=findViewById(R.id.btnShare);
        foto=findViewById(R.id.ivFoto1);
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

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassBarang");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
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
                listClassBarang.get(indeks).dilihat+=1;
                String hargaasli = String.format("%,d", listClassBarang.get(indeks).harga);
                nama.setText(listClassBarang.get(indeks).namabarang);
                hargabarang.setText("Rp. "+hargaasli);

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
                    changeFragment(new FragmentCommentBarang(), listClassBarang, listClassUser);
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
        if(aktif.equals("1")){
            Intent i = new Intent(InfoBarang.this,ChatRoom.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Login terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }
    public void preview(View view) {
        Intent i = new Intent(InfoBarang.this,PreviewActivity.class);
        startActivity(i);
    }
    public void beli(View view) {
        Intent i = new Intent(InfoBarang.this,TransactionActivity.class);
        startActivity(i);
    }

    public void addwishlist(View view) {
        if(aktif.equals("1")){
            String useryangbeli="",barangyangdibeli="",harga="";
            for (int i = 0; i < listClassUser.size(); i++) {
//                if(listClassUser.get(i).aktif.equals("1")){
//                    useryangbeli = listClassUser.get(i).nama;
//                }
            }
            barangyangdibeli=nama.getText().toString();
            harga=hargabarang.getText().toString();
            if(listWishlist.size()==0){
                listWishlist.add(new ClassWishlist(barangyangdibeli,harga,useryangbeli));
                Toast.makeText(this, "ClassBarang berhasil ditambahkan ke Wishlist", Toast.LENGTH_SHORT).show();
            }
            else{
                jumlah=0;
                for (int i = 0; i < listWishlist.size(); i++) {
                    if(listWishlist.get(i).yangbeli.equals(useryangbeli)){
                        jumlah++;
                    }
                }
                if(jumlah>0){
                    for (int i = 0; i < listWishlist.size(); i++) {
                        if(listWishlist.get(i).yangbeli.equals(useryangbeli) && listWishlist.get(i).namabarang.equals(nama.getText().toString())){
                            Toast.makeText(this, "ClassBarang sudah ada di Wishlist", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        else if(i+1==listWishlist.size()){
                            Toast.makeText(this, "ClassBarang berhasil ditambahkan ke Wishlist", Toast.LENGTH_SHORT).show();
                            listWishlist.add(new ClassWishlist(barangyangdibeli,harga,useryangbeli));
                            break;
                        }
                    }
                }
                else{
                    listWishlist.add(new ClassWishlist(barangyangdibeli,harga,useryangbeli));
                    Toast.makeText(this, "ClassBarang berhasil ditambahkan ke Wishlist", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else {
            Toast.makeText(this, "Login terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }

    public void share(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, listClassBarang.get(indeks).namabarang+"\n"+ listClassBarang.get(indeks).harga );
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share"));
    }

    public void changeFragment(Fragment f, ArrayList<ClassBarang> listClassBarang, ArrayList<ClassUser> listClassUser){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("listClassBarang", listClassBarang);
        bundle.putSerializable("listClassUser", listClassUser);
        f.setArguments(bundle);
        ft.replace(R.id.containerBarang, f);
        ft.commit();
    }
}