package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class WishList extends AppCompatActivity {
    ArrayList<ClassUser> listClassUser = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassBarang> filterClassBarang = new ArrayList<>();
    ArrayList<ClassToko> listClassToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlistku = new ArrayList<>();
    TextView total;
    Button beli;
    DatabaseReference databaseReference_barang,databaseReference_user,databaseReference_wishlist;
    ArrayList<String>barangtampung = new ArrayList<>();
    Button tambah,kurang,hapus,tambah2,kurang2,hapus2;
    CheckBox pilih,pilih2;
    ImageView foto,foto2;
    TextView nama,harga,jumlah,nama2,harga2,jumlah2;
    Boolean pilihbarang1=false,pilihbarang2=false;

    String akunyangbeli="",idbarang="",idbarang2="",toko="";
    int intharga=0,intharga2=0,jumlahtampung=0,jumlahtampung2=0;
    int intjumlah=0,intjumlah2=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        beli=findViewById(R.id.btnBeli);
        tambah=findViewById(R.id.btnTambah);
        tambah2=findViewById(R.id.btnTambah2);
        kurang=findViewById(R.id.btnKurang);
        kurang2=findViewById(R.id.btnKurang2);
        hapus=findViewById(R.id.btnHapus);
        hapus2=findViewById(R.id.btnHapus2);
        pilih=findViewById(R.id.cbPilih);
        pilih2=findViewById(R.id.cbPilih2);
        foto=findViewById(R.id.ivBarang);
        foto2=findViewById(R.id.ivBarang2);
        nama=findViewById(R.id.tvNamaBarang);
        nama2=findViewById(R.id.tvNamaBarang2);
        harga=findViewById(R.id.tvHargaBarang);
        harga2=findViewById(R.id.tvHargaBarang2);
        jumlah=findViewById(R.id.tvJumlah);
        jumlah2=findViewById(R.id.tvJumlah2);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setColor(Color.BLACK);
        beli.setBackground(drawable3);

        tambah.setVisibility(View.INVISIBLE);
        kurang.setVisibility(View.INVISIBLE);
        hapus.setVisibility(View.INVISIBLE);
        pilih.setVisibility(View.INVISIBLE);
        foto.setVisibility(View.INVISIBLE);
        nama.setVisibility(View.INVISIBLE);
        harga.setVisibility(View.INVISIBLE);
        jumlah.setVisibility(View.INVISIBLE);
        tambah2.setVisibility(View.INVISIBLE);
        kurang2.setVisibility(View.INVISIBLE);
        hapus2.setVisibility(View.INVISIBLE);
        pilih2.setVisibility(View.INVISIBLE);
        foto2.setVisibility(View.INVISIBLE);
        nama2.setVisibility(View.INVISIBLE);
        harga2.setVisibility(View.INVISIBLE);
        jumlah2.setVisibility(View.INVISIBLE);


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

        FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
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

                for (int i = 0; i < listWishlistku.size(); i++) {
                    for (int j = 0; j < listClassBarang.size(); j++) {
                        if(listWishlistku.get(i).idbarang.equals(listClassBarang.get(j).idbarang)){
                            filterClassBarang.add(listClassBarang.get(j));
                        }
                    }
                }

                if(listWishlistku.size()>1){
                    tambah.setVisibility(View.VISIBLE);
                    kurang.setVisibility(View.VISIBLE);
                    hapus.setVisibility(View.VISIBLE);
                    pilih.setVisibility(View.VISIBLE);
                    foto.setVisibility(View.VISIBLE);
                    nama.setVisibility(View.VISIBLE);
                    harga.setVisibility(View.VISIBLE);
                    jumlah.setVisibility(View.VISIBLE);
                    tambah2.setVisibility(View.VISIBLE);
                    kurang2.setVisibility(View.VISIBLE);
                    hapus2.setVisibility(View.VISIBLE);
                    pilih2.setVisibility(View.VISIBLE);
                    foto2.setVisibility(View.VISIBLE);
                    nama2.setVisibility(View.VISIBLE);
                    harga2.setVisibility(View.VISIBLE);
                    jumlah2.setVisibility(View.VISIBLE);

                    String hargaasli = String.format("%,d", filterClassBarang.get(0).harga);

                    nama.setText(filterClassBarang.get(0).namabarang);
                    harga.setText("Rp. "+hargaasli);
                    FirebaseStorage.getInstance().getReference().child("ProfilePicture").child(filterClassBarang.get(0).idbarang).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Glide.with(getApplicationContext()).load(uri).into(foto
                            );
                        }
                    });
                    String hargaasli2 = String.format("%,d", filterClassBarang.get(1).harga);
                    nama2.setText(filterClassBarang.get(1).namabarang);
                    harga2.setText("Rp. "+hargaasli2);
                    FirebaseStorage.getInstance().getReference().child("ProfilePicture").child(filterClassBarang.get(1).idbarang).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Glide.with(getApplicationContext()).load(uri).into(foto2
                            );
                        }
                    });
                }
                else if(listWishlistku.size()==1){
                    tambah.setVisibility(View.VISIBLE);
                    kurang.setVisibility(View.VISIBLE);
                    hapus.setVisibility(View.VISIBLE);
                    pilih.setVisibility(View.VISIBLE);
                    foto.setVisibility(View.VISIBLE);
                    nama.setVisibility(View.VISIBLE);
                    harga.setVisibility(View.VISIBLE);
                    jumlah.setVisibility(View.VISIBLE);


                    String hargaasli = String.format("%,d", filterClassBarang.get(0).harga);

                    nama.setText(filterClassBarang.get(0).namabarang);
                    harga.setText("Rp. "+hargaasli);
                    FirebaseStorage.getInstance().getReference().child("ProfilePicture").child(filterClassBarang.get(0).idbarang).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Glide.with(getApplicationContext()).load(uri).into(foto
                            );
                        }
                    });
                }
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
        if(pilihbarang1==false && pilihbarang2==false){
            Toast.makeText(this, "Pilih 1 barang untuk dibeli", Toast.LENGTH_SHORT).show();
        }
        else if(pilihbarang1==true && pilihbarang2==false && intjumlah>0){
            FirebaseDatabase.getInstance().getReference().child("ClassToko").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()) {
                        if(ds.child("email").getValue().toString().equals(filterClassBarang.get(0).namatoko)){
                            String akunyangbeli=FirebaseAuth.getInstance().getCurrentUser().getEmail();
                            String idbarang=filterClassBarang.get(0).idbarang;
                            int harga=filterClassBarang.get(0).harga;
                            int jumlah = intjumlah;
                            String toko = ds.child("email").getValue().toString();

                            Intent i = new Intent(WishList.this, DetailAlamat.class);
                            i.putExtra("akunyangbeli",akunyangbeli);
                            i.putExtra("idbarang",idbarang);
                            i.putExtra("harga",harga);
                            i.putExtra("jumlah",jumlah);
                            i.putExtra("toko",toko);
                            startActivity(i);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if(pilihbarang1==false && pilihbarang2==true && intjumlah2>0){
            FirebaseDatabase.getInstance().getReference().child("ClassToko").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()) {
                        if(ds.child("email").getValue().toString().equals(filterClassBarang.get(1).namatoko)){
                            String akunyangbeli=FirebaseAuth.getInstance().getCurrentUser().getEmail();
                            String idbarang=filterClassBarang.get(1).idbarang;
                            int harga=filterClassBarang.get(1).harga;
                            int jumlah = intjumlah2;
                            String toko = ds.child("email").getValue().toString();

                            Intent i = new Intent(WishList.this, DetailAlamat.class);
                            i.putExtra("akunyangbeli",akunyangbeli);
                            i.putExtra("idbarang",idbarang);
                            i.putExtra("harga",harga);
                            i.putExtra("jumlah",jumlah);
                            i.putExtra("toko",toko);
                            startActivity(i);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if(pilihbarang1==true && pilihbarang2==true && intjumlah>0 && intjumlah2>0){
            FirebaseDatabase.getInstance().getReference().child("ClassToko").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()) {
                        if(ds.child("email").getValue().toString().equals(filterClassBarang.get(0).namatoko)){
                            akunyangbeli=FirebaseAuth.getInstance().getCurrentUser().getEmail();
                            idbarang=filterClassBarang.get(0).idbarang;
                            jumlahtampung = intjumlah;
                            intharga=filterClassBarang.get(0).harga;
                            toko = ds.child("email").getValue().toString();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            FirebaseDatabase.getInstance().getReference().child("ClassToko").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()) {
                        if(ds.child("email").getValue().toString().equals(filterClassBarang.get(1).namatoko)){
                            idbarang2=filterClassBarang.get(1).idbarang;
                            jumlahtampung2 = intjumlah2;
                            intharga2=filterClassBarang.get(1).harga;
                        }
                    }
                    Intent i = new Intent(WishList.this, DetailAlamat.class);
                    i.putExtra("akunyangbeli",akunyangbeli);
                    i.putExtra("idbarang1",idbarang);
                    i.putExtra("idbarang2",idbarang2);
                    i.putExtra("harga",intharga);
                    i.putExtra("harga2",intharga2);
                    i.putExtra("jumlah",jumlahtampung);
                    i.putExtra("jumlah2",jumlahtampung2);
                    i.putExtra("toko",toko);
                    startActivity(i);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            Toast.makeText(this, "jumlah barang yang dibeli minimal 1", Toast.LENGTH_SHORT).show();
        }
    }

    public void tambah1(View view) {
        intjumlah++;
        jumlah.setText(intjumlah+"");
    }

    public void hapus1(View view) {
        tambah.setVisibility(View.INVISIBLE);
        kurang.setVisibility(View.INVISIBLE);
        hapus.setVisibility(View.INVISIBLE);
        pilih.setVisibility(View.INVISIBLE);
        foto.setVisibility(View.INVISIBLE);
        nama.setVisibility(View.INVISIBLE);
        harga.setVisibility(View.INVISIBLE);
        jumlah.setVisibility(View.INVISIBLE);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassWishlist");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("emailpembeli").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())&&ds.child("idbarang").getValue().toString().equals(filterClassBarang.get(0).idbarang)){
                        ClassWishlist updatewishlist = new ClassWishlist();
                        updatewishlist.setEmailpembeli("kosong@gmail.com");
                        updatewishlist.setIdbarang("");
                        databaseReference.child(ds.getKey()).setValue(updatewishlist);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void kurang1(View view) {
        if(intjumlah-1>=0){
            intjumlah--;
            jumlah.setText(intjumlah+"");
        }
    }

    public void pilih1(View view) {
        if(pilihbarang1==true){
            pilihbarang1=false;
        }
        else{
            pilihbarang1=true;
        }
    }

    public void pilih2(View view) {
        if(pilihbarang2==true){
            pilihbarang2=false;
        }
        else{
            pilihbarang2=true;
        }
    }

    public void kurang2(View view) {
        if(intjumlah2-1>=0){
            intjumlah2--;
            jumlah2.setText(intjumlah2+"");
        }
    }

    public void hapus2(View view) {
        tambah2.setVisibility(View.INVISIBLE);
        kurang2.setVisibility(View.INVISIBLE);
        hapus2.setVisibility(View.INVISIBLE);
        pilih2.setVisibility(View.INVISIBLE);
        foto2.setVisibility(View.INVISIBLE);
        nama2.setVisibility(View.INVISIBLE);
        harga2.setVisibility(View.INVISIBLE);
        jumlah2.setVisibility(View.INVISIBLE);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassWishlist");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("emailpembeli").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())&&ds.child("idbarang").getValue().toString().equals(filterClassBarang.get(1).idbarang)){
                        ClassWishlist updatewishlist = new ClassWishlist();
                        updatewishlist.setEmailpembeli("kosong@gmail.com");
                        updatewishlist.setIdbarang("");
                        databaseReference.child(ds.getKey()).setValue(updatewishlist);
                    }
                }
            }

            @Override

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void tambah2(View view) {
        intjumlah2++;
        jumlah2.setText(intjumlah2+"");
    }
}