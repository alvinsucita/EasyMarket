package com.example.easymarket;


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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    ImageView fototoko,b1,b2,b3,b4,b5;
    TextView namatoko,daerahtoko,rating,transaksi;
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassToko> listClassToko = new ArrayList<>();

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

        FirebaseDatabase.getInstance().getReference().child("ClassToko").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        ClassToko semua_Class_Toko =new ClassToko();
                        semua_Class_Toko.setDaerahasal(ds.child("daerahasal").getValue().toString());
                        semua_Class_Toko.setNama(ds.child("nama").getValue().toString());
                        semua_Class_Toko.setEmail(ds.child("email").getValue().toString());
                        semua_Class_Toko.setAktif(ds.child("aktif").getValue().toString());
                        semua_Class_Toko.setRating(Integer.parseInt(ds.child("rating").getValue().toString()));
                        listClassToko.add(semua_Class_Toko);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        if(!FirebaseAuth.getInstance().getCurrentUser().getEmail().equals("guest@guest.com")){

        }
    }
}
