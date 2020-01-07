package com.example.easymarket;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInfoToko extends Fragment {


    public FragmentInfoToko() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_info_toko, container, false);
    }

    ImageView fototoko,b1,b2,b3,b4,b5,verifikasi;
    TextView namatoko,daerahtoko,rating,transaksi;
    ArrayList<ClassNota> listClassNota = new ArrayList<>();
    ArrayList<ClassToko> listClassToko = new ArrayList<>();
    ArrayList<ClassRating> listClassRating = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final InfoBarang infoBarang = (InfoBarang) getActivity();

        fototoko=view.findViewById(R.id.ivGambarToko);
        b1=view.findViewById(R.id.ivRating1);
        b2=view.findViewById(R.id.ivRating2);
        b3=view.findViewById(R.id.ivRating3);
        b4=view.findViewById(R.id.ivRating4);
        b5=view.findViewById(R.id.ivRating5);
        namatoko=view.findViewById(R.id.tvNamaToko);
        daerahtoko=view.findViewById(R.id.tvDaerahToko);
        rating=view.findViewById(R.id.tvRatingToko);
        transaksi=view.findViewById(R.id.tvTransaksiToko);
        verifikasi=view.findViewById(R.id.ivVerifikasiToko);

        FirebaseDatabase.getInstance().getReference().child("ClassNota").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("namatoko").getValue().toString().equals(infoBarang.listClassBarang.get(infoBarang.indeks).toko)&&Integer.parseInt(ds.child("posisi").getValue().toString())==3){
                        ClassNota semua_Class_Nota =new ClassNota();
                        semua_Class_Nota.setIdnota((ds.child("idnota").getValue().toString()));
                        semua_Class_Nota.setNamatoko((ds.child("namatoko").getValue().toString()));
                        semua_Class_Nota.setIdbarang((ds.child("idbarang").getValue().toString()));
                        semua_Class_Nota.setNamauser(ds.child("namauser").getValue().toString());
                        semua_Class_Nota.setAlamat((ds.child("alamat").getValue().toString()));
                        semua_Class_Nota.setPembayaran((ds.child("pembayaran").getValue().toString()));
                        semua_Class_Nota.setJenispengiriman((ds.child("jenispengiriman").getValue().toString()));
                        semua_Class_Nota.setHargabarang(Integer.parseInt(ds.child("hargabarang").getValue().toString()));
                        semua_Class_Nota.setJumlahbarang(Integer.parseInt(ds.child("jumlahbarang").getValue().toString()));
                        semua_Class_Nota.setHargapengiriman(Integer.parseInt(ds.child("hargapengiriman").getValue().toString()));
                        semua_Class_Nota.setTotal(Integer.parseInt(ds.child("total").getValue().toString()));
                        semua_Class_Nota.setPosisi(Integer.parseInt(ds.child("posisi").getValue().toString()));
                        listClassNota.add(semua_Class_Nota);
                    }
                }
                transaksi.setText("Transaksi Berhasil : "+listClassNota.size()+" kali");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("ClassRating").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("toko").getValue().toString().equals(infoBarang.listClassBarang.get(infoBarang.indeks).toko)){
                        ClassRating semuaRating =new ClassRating();
                        semuaRating.setRating(Integer.parseInt(ds.child("rating").getValue().toString()));
                        semuaRating.setToko(ds.child("toko").getValue().toString());
                        semuaRating.setYangrating(ds.child("yangrating").getValue().toString());
                        listClassRating.add(semuaRating);
                    }
                }

                int jumlahRating =0;
                for (int i = 0; i < listClassRating.size(); i++) {
                    jumlahRating=jumlahRating+listClassRating.get(i).rating;
                }
                int totalRating = jumlahRating/listClassRating.size();
                rating.setText("Rating : "+totalRating+" / 5");

                int ratingDariUser=0;
                for (int i = 0; i < listClassRating.size(); i++) {
                    if(listClassRating.get(i).yangrating.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        ratingDariUser=listClassRating.get(i).rating;
                    }
                }

                if(ratingDariUser==1){
                    b1.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_border_black_24dp);

                    b1.setImageResource(R.drawable.ic_star_black_24dp);
                }
                else if(ratingDariUser==2){
                    b1.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_border_black_24dp);

                    b1.setImageResource(R.drawable.ic_star_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_black_24dp);
                }
                else if(ratingDariUser==3){
                    b1.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_border_black_24dp);

                    b1.setImageResource(R.drawable.ic_star_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_black_24dp);
                }
                else if(ratingDariUser==4){
                    b1.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_border_black_24dp);

                    b1.setImageResource(R.drawable.ic_star_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_black_24dp);
                }
                else if(ratingDariUser==5){
                    b1.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_border_black_24dp);

                    b1.setImageResource(R.drawable.ic_star_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_black_24dp);
                }
                else{
                    b1.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_border_black_24dp);
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
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("email").getValue().toString().equals(infoBarang.listClassBarang.get(infoBarang.indeks).toko)){
                        ClassToko semua_Class_Toko =new ClassToko();
                        semua_Class_Toko.setDaerahasal(ds.child("daerahasal").getValue().toString());
                        semua_Class_Toko.setNama(ds.child("nama").getValue().toString());
                        semua_Class_Toko.setEmail(ds.child("email").getValue().toString());
                        semua_Class_Toko.setAktif(ds.child("aktif").getValue().toString());
                        semua_Class_Toko.setRating(Integer.parseInt(ds.child("rating").getValue().toString()));
                        listClassToko.add(semua_Class_Toko);
                    }
                }
                namatoko.setText(listClassToko.get(0).nama);
                daerahtoko.setText("Daerah Toko : "+listClassToko.get(0).daerahasal);
                if(!listClassToko.get(0).aktif.equals("2")){
                    verifikasi.setVisibility(View.INVISIBLE);
                }

                FirebaseStorage.getInstance().getReference().child("ProfilePicture").child(infoBarang.listClassBarang.get(infoBarang.indeks).toko).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(getContext()).load(uri).into(fototoko);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        fototoko.setBackgroundResource(R.drawable.ic_account_circle_black_24dp);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!FirebaseAuth.getInstance().getCurrentUser().getEmail().equals("guest@guest.com")){
                    b1.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_border_black_24dp);

                    b1.setImageResource(R.drawable.ic_star_black_24dp);

                    int cek=0;
                    for (int i = 0; i < listClassRating.size(); i++) {
                        if(listClassRating.get(i).yangrating.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                            cek++;
                        }
                    }

                    if(cek==0){
                        ClassRating ratingbaru = new ClassRating(FirebaseAuth.getInstance().getCurrentUser().getEmail(),infoBarang.listClassBarang.get(infoBarang.indeks).toko,1);
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassRating");
                        final String key=databaseReference.push().getKey();
                        databaseReference.child(key).setValue(ratingbaru);
                        Toast.makeText(FragmentInfoToko.this.getContext(), "Anda Memberikan Rating 1 untuk Toko ini", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassRating");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Boolean cek = true;
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(ds.child("yangrating").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                        ClassRating updaterating = new ClassRating();
                                        updaterating.setYangrating(ds.child("yangrating").getValue().toString());
                                        updaterating.setRating(1);
                                        updaterating.setToko(ds.child("toko").getValue().toString());
                                        databaseReference.child(ds.getKey()).setValue(updaterating).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(FragmentInfoToko.this.getContext(), "Anda Memberikan Rating 1 untuk Toko ini", Toast.LENGTH_SHORT).show();
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
                else{
                    Toast.makeText(infoBarang, "Login Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!FirebaseAuth.getInstance().getCurrentUser().getEmail().equals("guest@guest.com")){
                    b1.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_border_black_24dp);

                    b1.setImageResource(R.drawable.ic_star_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_black_24dp);

                    int cek=0;
                    for (int i = 0; i < listClassRating.size(); i++) {
                        if(listClassRating.get(i).yangrating.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                            cek++;
                        }
                    }

                    if(cek==0){
                        ClassRating ratingbaru = new ClassRating(FirebaseAuth.getInstance().getCurrentUser().getEmail(),infoBarang.listClassBarang.get(infoBarang.indeks).toko,2);
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassRating");
                        final String key=databaseReference.push().getKey();
                        databaseReference.child(key).setValue(ratingbaru);
                        Toast.makeText(FragmentInfoToko.this.getContext(), "Anda Memberikan Rating 2 untuk Toko ini", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassRating");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Boolean cek = true;
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(ds.child("yangrating").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                        ClassRating updaterating = new ClassRating();
                                        updaterating.setYangrating(ds.child("yangrating").getValue().toString());
                                        updaterating.setRating(2);
                                        updaterating.setToko(ds.child("toko").getValue().toString());
                                        databaseReference.child(ds.getKey()).setValue(updaterating).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(FragmentInfoToko.this.getContext(), "Anda Memberikan Rating 2 untuk Toko ini", Toast.LENGTH_SHORT).show();
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
                else{
                    Toast.makeText(infoBarang, "Login Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!FirebaseAuth.getInstance().getCurrentUser().getEmail().equals("guest@guest.com")){
                    b1.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_border_black_24dp);

                    b1.setImageResource(R.drawable.ic_star_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_black_24dp);

                    int cek=0;
                    for (int i = 0; i < listClassRating.size(); i++) {
                        if(listClassRating.get(i).yangrating.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                            cek++;
                        }
                    }

                    if(cek==0){
                        ClassRating ratingbaru = new ClassRating(FirebaseAuth.getInstance().getCurrentUser().getEmail(),infoBarang.listClassBarang.get(infoBarang.indeks).toko,3);
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassRating");
                        final String key=databaseReference.push().getKey();
                        databaseReference.child(key).setValue(ratingbaru);
                        Toast.makeText(FragmentInfoToko.this.getContext(), "Anda Memberikan Rating 3 untuk Toko ini", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassRating");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Boolean cek = true;
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(ds.child("yangrating").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                        ClassRating updaterating = new ClassRating();
                                        updaterating.setYangrating(ds.child("yangrating").getValue().toString());
                                        updaterating.setRating(3);
                                        updaterating.setToko(ds.child("toko").getValue().toString());
                                        databaseReference.child(ds.getKey()).setValue(updaterating).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(FragmentInfoToko.this.getContext(), "Anda Memberikan Rating 3 untuk Toko ini", Toast.LENGTH_SHORT).show();
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
                else{
                    Toast.makeText(infoBarang, "Login Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!FirebaseAuth.getInstance().getCurrentUser().getEmail().equals("guest@guest.com")){
                    b1.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_border_black_24dp);

                    b1.setImageResource(R.drawable.ic_star_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_black_24dp);

                    int cek=0;
                    for (int i = 0; i < listClassRating.size(); i++) {
                        if(listClassRating.get(i).yangrating.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                            cek++;
                        }
                    }

                    if(cek==0){
                        ClassRating ratingbaru = new ClassRating(FirebaseAuth.getInstance().getCurrentUser().getEmail(),infoBarang.listClassBarang.get(infoBarang.indeks).toko,4);
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassRating");
                        final String key=databaseReference.push().getKey();
                        databaseReference.child(key).setValue(ratingbaru);
                        Toast.makeText(FragmentInfoToko.this.getContext(), "Anda Memberikan Rating 4 untuk Toko ini", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassRating");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Boolean cek = true;
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(ds.child("yangrating").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                        ClassRating updaterating = new ClassRating();
                                        updaterating.setYangrating(ds.child("yangrating").getValue().toString());
                                        updaterating.setRating(4);
                                        updaterating.setToko(ds.child("toko").getValue().toString());
                                        databaseReference.child(ds.getKey()).setValue(updaterating).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(FragmentInfoToko.this.getContext(), "Anda Memberikan Rating 4 untuk Toko ini", Toast.LENGTH_SHORT).show();
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
                else{
                    Toast.makeText(infoBarang, "Login Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!FirebaseAuth.getInstance().getCurrentUser().getEmail().equals("guest@guest.com")){
                    b1.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_border_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_border_black_24dp);

                    b1.setImageResource(R.drawable.ic_star_black_24dp);
                    b2.setImageResource(R.drawable.ic_star_black_24dp);
                    b3.setImageResource(R.drawable.ic_star_black_24dp);
                    b4.setImageResource(R.drawable.ic_star_black_24dp);
                    b5.setImageResource(R.drawable.ic_star_black_24dp);

                    int cek=0;
                    for (int i = 0; i < listClassRating.size(); i++) {
                        if(listClassRating.get(i).yangrating.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                            cek++;
                        }
                    }

                    if(cek==0){
                        ClassRating ratingbaru = new ClassRating(FirebaseAuth.getInstance().getCurrentUser().getEmail(),infoBarang.listClassBarang.get(infoBarang.indeks).toko,5);
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassRating");
                        final String key=databaseReference.push().getKey();
                        databaseReference.child(key).setValue(ratingbaru);
                        Toast.makeText(FragmentInfoToko.this.getContext(), "Anda Memberikan Rating 5 untuk Toko ini", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassRating");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Boolean cek = true;
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(ds.child("yangrating").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                        ClassRating updaterating = new ClassRating();
                                        updaterating.setYangrating(ds.child("yangrating").getValue().toString());
                                        updaterating.setRating(5);
                                        updaterating.setToko(ds.child("toko").getValue().toString());
                                        databaseReference.child(ds.getKey()).setValue(updaterating).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(FragmentInfoToko.this.getContext(), "Anda Memberikan Rating 5 untuk Toko ini", Toast.LENGTH_SHORT).show();
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
                else{
                    Toast.makeText(infoBarang, "Login Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
