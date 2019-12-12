package com.example.easymarket;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.icu.text.IDNA;
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

import com.google.android.gms.common.util.Base64Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class  FragmentCommentBarang extends Fragment {

    EditText isicomment;
    Button tambahkomen;
    RecyclerView rv;
    AdapterComment adapterComment;
    ArrayList<ClassComment>listClassComment = new ArrayList<>();
    ArrayList<ClassUser>listClassUser = new ArrayList<>();
    ArrayList<ClassBarang>listClassBarang = new ArrayList<>();
    ArrayList<ClassComment>filterComment= new ArrayList<>();
    Context context;

    public FragmentCommentBarang(Context contextku) {
        this.context = contextku;
    }

    public FragmentCommentBarang() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_comment_barang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final InfoBarang infoBarang = (InfoBarang) getActivity();

        rv = view.findViewById(R.id.rvcomment);
        isicomment=view.findViewById(R.id.etEditComment);
        tambahkomen=view.findViewById(R.id.btnComment);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(25);
        drawable.setStroke(8, Color.LTGRAY);
        drawable.setColor(Color.WHITE);
        isicomment.setBackground(drawable);

        FirebaseDatabase.getInstance().getReference().child("ClassComment").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("idbarang").getValue().toString().equals(infoBarang.listClassBarang.get(infoBarang.indeks).idbarang)){
                        ClassComment komenku =new ClassComment();
                        komenku.setIdbarang(ds.child("idbarang").getValue().toString());
                        komenku.setIsi(ds.child("isi").getValue().toString());
                        komenku.setNama(ds.child("nama").getValue().toString());
                        filterComment.add(komenku);
                    }
                }
                final AdapterComment adapterComment= new AdapterComment(filterComment);
                rv.setHasFixedSize(true);
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(adapterComment);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tambahkomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isicomment.getText().toString().equals("")){
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
                                Toast.makeText(getContext(), "Login terlebih dahulu", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassUser");
                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                                            if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                                String  namayangkomen = ds.child("nama").getValue().toString();

                                                ClassComment commentbaru = new ClassComment(infoBarang.listClassBarang.get(infoBarang.indeks).idbarang,namayangkomen,isicomment.getText().toString());
                                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassComment");
                                                final String key=databaseReference.push().getKey();
                                                databaseReference.child(key).setValue(commentbaru);

                                                FirebaseDatabase.getInstance().getReference().child("ClassComment").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        Boolean cek = true;
                                                        filterComment.clear();
                                                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                                                            if(ds.child("idbarang").getValue().toString().equals(infoBarang.listClassBarang.get(infoBarang.indeks).idbarang)){
                                                                ClassComment komenku =new ClassComment();
                                                                komenku.setIdbarang(ds.child("idbarang").getValue().toString());
                                                                komenku.setIsi(ds.child("isi").getValue().toString());
                                                                komenku.setNama(ds.child("nama").getValue().toString());
                                                                filterComment.add(komenku);
                                                            }
                                                        }
                                                        final AdapterComment adapterComment= new AdapterComment(filterComment);
                                                        rv.setHasFixedSize(true);
                                                        rv.setLayoutManager(new LinearLayoutManager(getContext()));
                                                        rv.setAdapter(adapterComment);
                                                        isicomment.setText("");
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

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else{
                    Toast.makeText(getContext(), "Komen tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
