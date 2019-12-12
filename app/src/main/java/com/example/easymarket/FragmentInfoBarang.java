package com.example.easymarket;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class FragmentInfoBarang extends Fragment {

    public FragmentInfoBarang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_info_barang, container, false);
    }

    TextView isi,likes,dilihat,dibeli;
    Button btnLikes,btndislikes;
    public ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    public ArrayList<ClassUser> listClassUser= new ArrayList<>();
    public ArrayList<ClassLikes> listClassLikes= new ArrayList<>();
    DatabaseReference databaseReference;
    ImageView fotobarang;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final InfoBarang infoBarang = (InfoBarang) getActivity();

        isi=view.findViewById(R.id.tvIsiDeskripsi);
        likes=view.findViewById(R.id.tvLikesBarang);
        dilihat=view.findViewById(R.id.tvBarangDilihat);
        dibeli=view.findViewById(R.id.tvBarangTerjual);
        btnLikes=view.findViewById(R.id.btnLikes);
        btndislikes=view.findViewById(R.id.btnDislikes);
        fotobarang=view.findViewById(R.id.ivFoto);

        likes.setText("");
        dilihat.setText("");
        dibeli.setText("");
        isi.setText("");

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
                likes.setText("Likes : "+ listClassBarang.get(infoBarang.indeks).likes);
                dilihat.setText("Dilihat : "+ listClassBarang.get(infoBarang.indeks).dilihat+" kali");
                dibeli.setText("Terjual : "+ listClassBarang.get(infoBarang.indeks).dibeli+" kali");
                isi.setText(listClassBarang.get(infoBarang.indeks).deskripsi+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassLikes");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassLikes semua_Class_likes =new ClassLikes();
                    semua_Class_likes.setEmailuser(ds.child("emailuser").getValue().toString());
                    semua_Class_likes.setIdbarang(ds.child("idbarang").getValue().toString());
                    listClassLikes.add(semua_Class_likes);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setColor(Color.WHITE);
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setStroke(5, Color.BLACK);
        drawable4.setCornerRadius(50);
        isi.setBackground(drawable4);

        btnLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                Toast.makeText(infoBarang, "Login terlebih dahulu", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                String emailuser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                                String idbarang = listClassBarang.get(infoBarang.indeks).idbarang;
                                int ctr=0;

                                FirebaseDatabase.getInstance().getReference().child("ClassLikes").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Boolean cek = true;
                                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                                            ClassLikes semua_Class_likes =new ClassLikes();
                                            semua_Class_likes.setEmailuser(ds.child("emailuser").getValue().toString());
                                            semua_Class_likes.setIdbarang(ds.child("idbarang").getValue().toString());
                                            listClassLikes.add(semua_Class_likes);
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                for (int i = 0; i < listClassLikes.size(); i++) {
                                    if(listClassLikes.get(i).emailuser.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()) &&listClassLikes.get(i).idbarang.equals(idbarang)){
                                        ctr++;
                                    }
                                }

                                if(ctr==0){
                                    ClassLikes likesbaru = new ClassLikes(idbarang,emailuser,1);
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassLikes");
                                    final String key=databaseReference.push().getKey();
                                    databaseReference.child(key).setValue(likesbaru);

                                    FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Boolean cek = true;
                                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                                if(ds.child("namabarang").getValue().toString().equals(infoBarang.nama.getText().toString())){
                                                    ClassBarang updatebarang = new ClassBarang();
                                                    updatebarang.setKategori(ds.child("kategori").getValue().toString());
                                                    updatebarang.setDeskripsi(ds.child("deskripsi").getValue().toString());
                                                    updatebarang.setDibeli(Integer.parseInt(ds.child("dibeli").getValue().toString()));
                                                    updatebarang.setDilihat(Integer.parseInt(ds.child("dilihat").getValue().toString()));
                                                    updatebarang.setHarga(Integer.parseInt(ds.child("harga").getValue().toString()));
                                                    updatebarang.setIdbarang(ds.child("idbarang").getValue().toString());
                                                    updatebarang.setLikes(Integer.parseInt(ds.child("likes").getValue().toString())+1);
                                                    updatebarang.setNamabarang(ds.child("namabarang").getValue().toString());
                                                    updatebarang.setNamatoko(ds.child("namatoko").getValue().toString());
                                                    updatebarang.setStok(Integer.parseInt(ds.child("stok").getValue().toString()));
                                                    FirebaseDatabase.getInstance().getReference().child("ClassBarang").child(ds.getKey()).setValue(updatebarang).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(getContext(), "Anda menyukai barang ini", Toast.LENGTH_SHORT).show();
                                                            //ubah tampilan agar likes bertambah 1
                                                            FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                    Boolean cek = true;
                                                                    listClassBarang.clear();
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
                                                                    likes.setText("Likes : "+ listClassBarang.get(infoBarang.indeks).likes);
                                                                    dilihat.setText("Dilihat : "+ listClassBarang.get(infoBarang.indeks).dilihat+" kali");
                                                                    dibeli.setText("Terjual : "+ listClassBarang.get(infoBarang.indeks).dibeli+" kali");
                                                                    isi.setText(listClassBarang.get(infoBarang.indeks).deskripsi+"");
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                                else{
                                    FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Boolean cek = true;
                                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                                if(ds.child("namabarang").getValue().toString().equals(infoBarang.nama.getText().toString())){
                                                    ClassBarang updatebarang = new ClassBarang();
                                                    updatebarang.setKategori(ds.child("kategori").getValue().toString());
                                                    updatebarang.setDeskripsi(ds.child("deskripsi").getValue().toString());
                                                    updatebarang.setDibeli(Integer.parseInt(ds.child("dibeli").getValue().toString()));
                                                    updatebarang.setDilihat(Integer.parseInt(ds.child("dilihat").getValue().toString()));
                                                    updatebarang.setHarga(Integer.parseInt(ds.child("harga").getValue().toString()));
                                                    updatebarang.setIdbarang(ds.child("idbarang").getValue().toString());
                                                    updatebarang.setLikes(Integer.parseInt(ds.child("likes").getValue().toString())-1);
                                                    updatebarang.setNamabarang(ds.child("namabarang").getValue().toString());
                                                    updatebarang.setNamatoko(ds.child("namatoko").getValue().toString());
                                                    updatebarang.setStok(Integer.parseInt(ds.child("stok").getValue().toString()));
                                                    FirebaseDatabase.getInstance().getReference().child("ClassBarang").child(ds.getKey()).setValue(updatebarang).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(getContext(), "Anda tidak jadi menyukai barang ini", Toast.LENGTH_SHORT).show();
                                                            //ubah tampilan agar likes bertambah 1
                                                            FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                    Boolean cek = true;
                                                                    listClassBarang.clear();
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

                                                                    likes.setText("Likes : "+ listClassBarang.get(infoBarang.indeks).likes);
                                                                    dilihat.setText("Dilihat : "+ listClassBarang.get(infoBarang.indeks).dilihat+" kali");
                                                                    dibeli.setText("Terjual : "+ listClassBarang.get(infoBarang.indeks).dibeli+" kali");
                                                                    isi.setText(listClassBarang.get(infoBarang.indeks).deskripsi+"");
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        
    }
}