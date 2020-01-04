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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInbox extends Fragment {


    public FragmentInbox() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }
    AdapterInboxToko adapterInboxToko;
    RecyclerView rvInboxToko;
    ArrayList<ClassNota> listClassNota = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvInboxToko = view.findViewById(R.id.rvInbox);

        final HomeToko homeToko = (HomeToko) getActivity();

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(25);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        rvInboxToko.setBackground(drawable3);

        FirebaseDatabase.getInstance().getReference().child("ClassNota").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("namatoko").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())&&Integer.parseInt(ds.child("posisi").getValue().toString())==2){
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
                        semua_Class_Nota.setPosisi(Integer.parseInt(ds.child("total").getValue().toString()));
                        listClassNota.add(semua_Class_Nota);
                    }
                }
                for (int i = 0; i < homeToko.listClassBarang.size(); i++) {
                    for (int j = 0; j < listClassNota.size(); j++) {
                        if(homeToko.listClassBarang.get(i).idbarang.equals(listClassNota.get(j).idbarang)){
                            listClassBarang.add(homeToko.listClassBarang.get(i));
                        }
                    }
                }
                rvInboxToko.setLayoutManager(new LinearLayoutManager(getContext()));
                adapterInboxToko=new AdapterInboxToko(listClassNota, listClassBarang, new RVClickListener() {
                    @Override
                    public void recyclerViewListBarangClick(View v, int posisi) {
                        Intent i = new Intent(FragmentInbox.this.getContext(),NotaActivity.class);
                        i.putExtra("liat",listClassNota);
                        i.putExtra("indeks",posisi);
                        i.putExtra("posisi",5);
                        startActivity(i);
                    }
                });
                rvInboxToko.setAdapter(adapterInboxToko);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
