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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {


    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_home, container, false);
    }


    Button filter;
    EditText search;
    RecyclerView rv;
    DatabaseReference databaseReference;
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarangSearches = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarangFilter = new ArrayList<>();
    Boolean adafilter=false;
    Boolean adasearch=false;
    AdapterMenuBarang adapterMenuBarang;
    RVClickListener rvcl;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Home home = (Home)getActivity();

        search = view.findViewById(R.id.etSearch);
        filter = view.findViewById(R.id.btnFilter);
        rv = view.findViewById(R.id.rvhome);




        databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassBarang");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
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
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                //AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarang,rvcl);
                adapterMenuBarang = new AdapterMenuBarang(listClassBarang, new RVClickListener() {
                    @Override
                    public void recyclerViewListBarangClick(View v, int posisi) {
                        int indeks=0;

                        if(adasearch==true){
                            if(!listClassBarangSearches.isEmpty()){
                                if(listClassBarangFilter.isEmpty()){
                                    for (int j = 0; j < listClassBarang.size(); j++) {
                                        if (listClassBarangSearches.get(posisi).idbarang.equals(listClassBarang.get(j).idbarang)) {
                                            indeks = j;
                                        }
                                    }
                                }
                                else{
                                    int temp=0;
                                    for (int j = 0; j < listClassBarangFilter.size(); j++) {
                                        if (listClassBarangSearches.get(posisi).idbarang.equals(listClassBarangFilter.get(j).idbarang)) {
                                            temp=j;
                                        }
                                    }
                                    for (int j = 0; j < listClassBarang.size(); j++) {
                                        if(listClassBarangFilter.get(temp).idbarang.equals(listClassBarang.get(j).idbarang)){
                                            indeks=j;
                                        }
                                    }
                                }
                            }
                            else{
                                indeks = posisi;
                            }
                        }
                        else if(adafilter==true){
                            if(!listClassBarangFilter.isEmpty()){
                                if(listClassBarangSearches.isEmpty()){
                                    for (int j = 0; j < listClassBarang.size(); j++) {
                                        if (listClassBarangFilter.get(posisi).idbarang.equals(listClassBarang.get(j).idbarang)) {
                                            indeks = j;
                                        }
                                    }
                                }
                                else{
                                    int temp=0;
                                    for (int j = 0; j < listClassBarangSearches.size(); j++) {
                                        if (listClassBarangFilter.get(posisi).idbarang.equals(listClassBarangSearches.get(j).idbarang)) {
                                            temp = j;
                                        }
                                    }
                                    for (int j = 0; j < listClassBarang.size(); j++) {
                                        if(listClassBarangSearches.get(temp).idbarang.equals(listClassBarang.get(j).idbarang)){
                                            indeks=j;
                                        }
                                    }
                                }
                            }
                            else{
                                indeks = posisi;
                            }
                        }
                        else{
                            indeks = posisi;
                        }

                        Intent i = new Intent(FragmentHome.this.getContext(),InfoBarang.class);
                        i.putExtra("indeks",indeks);
                        startActivity(i);
                    }
                });
                rv.setAdapter(adapterMenuBarang);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adasearch=true;
                adafilter=false;
                if(listClassBarangFilter.size()>0){
                    listClassBarangSearches.clear();
                    for (int j = 0; j < listClassBarangFilter.size(); j++) {
                        if(listClassBarangFilter.get(j).namabarang.toLowerCase().contains(search.getText().toString().toLowerCase())){
                            listClassBarangSearches.add(listClassBarangFilter.get(j));
                        }
                    }
                    AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangSearches);
                    rv.setAdapter(adapterMenuBarang);
                }
                else{
                    listClassBarangSearches.clear();
                    for (int j = 0; j < listClassBarang.size(); j++) {
                        if(listClassBarang.get(j).namabarang.toLowerCase().contains(search.getText().toString().toLowerCase())){
                            listClassBarangSearches.add(listClassBarang.get(j));
                        }
                    }
                    AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangSearches);
                    rv.setAdapter(adapterMenuBarang);
                }
            }
        });

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(8, Color.LTGRAY);
        drawable.setCornerRadius(100);
        search.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setStroke(8, Color.LTGRAY);
        drawable2.setColor(Color.WHITE);
        filter.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(25);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        rv.setBackground(drawable3);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popupmenu, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                adasearch=false;
                adafilter=true;
                if(item.getItemId()==R.id.itemPakaian){
                    if(listClassBarangSearches.size()>0){
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarangSearches.size(); j++) {
                            if(listClassBarangSearches.get(j).kategori.toLowerCase().contains("pakaian")){
                                listClassBarangFilter.add(listClassBarangSearches.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarang.size(); j++) {
                            if(listClassBarang.get(j).kategori.toLowerCase().contains("pakaian")){
                                listClassBarangFilter.add(listClassBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemIbuAnak){
                    if(listClassBarangSearches.size()>0){
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarangSearches.size(); j++) {
                            if(listClassBarangSearches.get(j).kategori.toLowerCase().contains("ibu")){
                                listClassBarangFilter.add(listClassBarangSearches.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarang.size(); j++) {
                            if(listClassBarang.get(j).kategori.toLowerCase().contains("ibu")){
                                listClassBarangFilter.add(listClassBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemElektronik){
                    if(listClassBarangSearches.size()>0){
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarangSearches.size(); j++) {
                            if(listClassBarangSearches.get(j).kategori.toLowerCase().contains("elektronik")){
                                listClassBarangFilter.add(listClassBarangSearches.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarang.size(); j++) {
                            if(listClassBarang.get(j).kategori.toLowerCase().contains("elektronik")){
                                listClassBarangFilter.add(listClassBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemMakananMinuman){
                    if(listClassBarangSearches.size()>0){
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarangSearches.size(); j++) {
                            if(listClassBarangSearches.get(j).kategori.toLowerCase().contains("makan")){
                                listClassBarangFilter.add(listClassBarangSearches.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarang.size(); j++) {
                            if(listClassBarang.get(j).kategori.toLowerCase().contains("makan")){
                                listClassBarangFilter.add(listClassBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemGames){
                    if(listClassBarangSearches.size()>0){
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarangSearches.size(); j++) {
                            if(listClassBarangSearches.get(j).kategori.toLowerCase().contains("games")){
                                listClassBarangFilter.add(listClassBarangSearches.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarang.size(); j++) {
                            if(listClassBarang.get(j).kategori.toLowerCase().contains("games")){
                                listClassBarangFilter.add(listClassBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemKecantikan){
                    if(listClassBarangSearches.size()>0){
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarangSearches.size(); j++) {
                            if(listClassBarangSearches.get(j).kategori.toLowerCase().contains("kecantikan")){
                                listClassBarangFilter.add(listClassBarangSearches.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarang.size(); j++) {
                            if(listClassBarang.get(j).kategori.toLowerCase().contains("kecantikan")){
                                listClassBarangFilter.add(listClassBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemOlahraga){
                    if(listClassBarangSearches.size()>0){
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarangSearches.size(); j++) {
                            if(listClassBarangSearches.get(j).kategori.toLowerCase().contains("olahraga")){
                                listClassBarangFilter.add(listClassBarangSearches.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                        adafilter=true;
                        adasearch=false;
                    }
                    else{
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarang.size(); j++) {
                            if(listClassBarang.get(j).kategori.toLowerCase().contains("olahraga")){
                                listClassBarangFilter.add(listClassBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemLainlain){
                    if(listClassBarangSearches.size()>0){
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarangSearches.size(); j++) {
                            if(listClassBarangSearches.get(j).kategori.toLowerCase().contains("lain")){
                                listClassBarangFilter.add(listClassBarangSearches.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listClassBarangFilter.clear();
                        for (int j = 0; j < listClassBarang.size(); j++) {
                            if(listClassBarang.get(j).kategori.toLowerCase().contains("lain")){
                                listClassBarangFilter.add(listClassBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemReset){
                    AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listClassBarang);
                    rv.setAdapter(adapterMenuBarang);

                    listClassBarangFilter.clear();
                    listClassBarangSearches.clear();
                    search.setText("");
                }
                return true;
            }
        });
    }
}
