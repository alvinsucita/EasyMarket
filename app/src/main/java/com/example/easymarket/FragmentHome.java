package com.example.easymarket;


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
    ArrayList<Barang> listBarang = new ArrayList<>();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Home home = (Home)getActivity();

        search = view.findViewById(R.id.etSearch);
        filter = view.findViewById(R.id.btnFilter);
        rv = view.findViewById(R.id.rvhome);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Barang");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    Barang semua_barang=new Barang();
                    semua_barang.setDeskripsi(ds.child("deskripsi").getValue().toString());
                    semua_barang.setDibeli(Integer.parseInt(ds.child("dibeli").getValue().toString()));
                    semua_barang.setDilihat(Integer.parseInt(ds.child("dilihat").getValue().toString()));
                    semua_barang.setHarga(Integer.parseInt(ds.child("harga").getValue().toString()));
                    semua_barang.setIdbarang(ds.child("idbarang").getValue().toString());
                    semua_barang.setKategori(ds.child("kategori").getValue().toString());
                    semua_barang.setLikes(Integer.parseInt(ds.child("likes").getValue().toString()));
                    semua_barang.setNamabarang(ds.child("namabarang").getValue().toString());
                    semua_barang.setNamatoko(ds.child("namatoko").getValue().toString());
                    semua_barang.setStok(Integer.parseInt(ds.child("stok").getValue().toString()));
                    listBarang.add(semua_barang);
                }
                Toast.makeText(home, listBarang.size()+"", Toast.LENGTH_SHORT).show();
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarang);
                rv.setAdapter(adapterMenuBarang);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
    }
}
