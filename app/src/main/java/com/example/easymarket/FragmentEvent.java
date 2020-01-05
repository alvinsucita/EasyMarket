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
import android.widget.Toast;

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
public class FragmentEvent extends Fragment {


    public FragmentEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_event, container, false);
    }

    RecyclerView rvevent;
    AdapterEvent adapterEvent;
    ArrayList<ClassEvent> listClassEvent = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Home home = (Home)getActivity();

        rvevent = view.findViewById(R.id.rvevent);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(25);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        rvevent.setBackground(drawable3);

        FirebaseDatabase.getInstance().getReference().child("ClassEvent").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassEvent semua_Class_Event =new ClassEvent();
                    semua_Class_Event.setJudul((ds.child("judul").getValue().toString()));
                    semua_Class_Event.setDesc((ds.child("desc").getValue().toString()));
                    listClassEvent.add(semua_Class_Event);
                }
                rvevent.setLayoutManager(new LinearLayoutManager(getContext()));
                adapterEvent=new AdapterEvent(listClassEvent, new RVClickListener() {
                    @Override
                    public void recyclerViewListBarangClick(View v, int posisi) {
                        Intent i = new Intent(FragmentEvent.this.getContext(),DetailEvent.class);
                        i.putExtra("indeks",posisi);
                        startActivity(i);
                    }
                });
                rvevent.setAdapter(adapterEvent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
