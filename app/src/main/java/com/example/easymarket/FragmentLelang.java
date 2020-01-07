package com.example.easymarket;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLelang extends Fragment {


    public FragmentLelang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_lelang, container, false);
    }

    EditText searchlelang;
    Button filterlelang;
    RecyclerView rvlelang;
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassBarang> filterBarang = new ArrayList<>();
    ArrayList<ClassLelang> listClassLelang = new ArrayList<>();
    AdapterBarangLelang adapterBarangLelang;

    ArrayList<ClassBarang> listClassBarangSearches = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarangFilter = new ArrayList<>();
    Boolean adafilter=false;
    Boolean adasearch=false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Home home = (Home)getActivity();

        searchlelang = view.findViewById(R.id.etSearchLelang);
        filterlelang = view.findViewById(R.id.btnFilterLelang);
        rvlelang = view.findViewById(R.id.rvlelang);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(8, Color.LTGRAY);
        drawable.setCornerRadius(100);
        searchlelang.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setStroke(8, Color.LTGRAY);
        drawable2.setColor(Color.WHITE);
        filterlelang.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(25);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        rvlelang.setBackground(drawable3);

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
                    semua_Class_barang.setToko(ds.child("toko").getValue().toString());
                    semua_Class_barang.setStok(Integer.parseInt(ds.child("stok").getValue().toString()));
                    listClassBarang.add(semua_Class_barang);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("ClassLelang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassLelang semua_Class_lelang =new ClassLelang();
                    semua_Class_lelang.setIdbarang(ds.child("idbarang").getValue().toString());
                    semua_Class_lelang.setHarganormal(Integer.parseInt(ds.child("harganormal").getValue().toString()));
                    semua_Class_lelang.setHargatertinggi(Integer.parseInt(ds.child("hargatertinggi").getValue().toString()));
                    semua_Class_lelang.setHargaawal(Integer.parseInt(ds.child("hargaawal").getValue().toString()));
                    semua_Class_lelang.setNamabidder(ds.child("namabidder").getValue().toString());
                    listClassLelang.add(semua_Class_lelang);
                }

                for (int i = 0; i < listClassLelang.size(); i++) {
                    for (int j = 0; j < listClassBarang.size(); j++) {
                        if(listClassLelang.get(i).idbarang.equals(listClassBarang.get(j).idbarang)){
                            filterBarang.add(listClassBarang.get(j));
                        }
                    }
                }
                rvlelang.setLayoutManager(new LinearLayoutManager(getContext()));

                adapterBarangLelang = new AdapterBarangLelang(filterBarang, new RVClickListener() {
                    @Override
                    public void recyclerViewListBarangClick(View v, int posisi) {
                        Intent i = new Intent(FragmentLelang.this.getContext(),InfoBarangLelang.class);
                        i.putExtra("indeks",posisi);
                        startActivity(i);
                    }
                });
                rvlelang.setAdapter(adapterBarangLelang);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
