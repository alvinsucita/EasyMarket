package com.example.easymarket;


import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotaPembayaranFragment extends Fragment {


    public NotaPembayaranFragment() {
        // Required empty public constructor
    }

    TextView show;
    ArrayList<ClassNota> listClassNota = new ArrayList<>();
    TextView metode,total,ongkir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_nota_pembayaran, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        metode=view.findViewById(R.id.tvnotametode);
        total=view.findViewById(R.id.tvnotatotalbarang);
        ongkir=view.findViewById(R.id.tvnotaongkos);

        FirebaseDatabase.getInstance().getReference().child("ClassNota").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
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
                    listClassNota.add(semua_Class_Nota);
                }

                String hargaasli = String.format("%,d",listClassNota.get(listClassNota.size()-1).hargabarang*listClassNota.get(listClassNota.size()-1).jumlahbarang);
                String hargaasli2 = String.format("%,d",listClassNota.get(listClassNota.size()-1).hargapengiriman);

                metode.setText(listClassNota.get(listClassNota.size()-1).pembayaran);
                total.setText("Rp. "+hargaasli);
                ongkir.setText("Rp. "+hargaasli2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
