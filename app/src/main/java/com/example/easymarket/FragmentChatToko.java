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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChatToko extends Fragment {


    public FragmentChatToko() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_chat_toko, container, false);
    }

    RecyclerView rv;
    ArrayList<ClassChat> listClassChat = new ArrayList<>();
    ArrayList<ClassChat> listClassChatFilter = new ArrayList<>();
    AdapterChat adapterChat;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final HomeToko homeToko = (HomeToko) getActivity();

        rv = view.findViewById(R.id.rvChatToko);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(25);
        drawable.setStroke(8, Color.LTGRAY);
        drawable.setColor(Color.WHITE);
        rv.setBackground(drawable);

        FirebaseDatabase.getInstance().getReference().child("ClassChat").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("yangdikirim").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        ClassChat chat =new ClassChat();
                        chat.setIsi(ds.child("isi").getValue().toString());
                        chat.setWaktu(ds.child("waktu").getValue().toString());
                        chat.setYangdikirim(ds.child("yangdikirim").getValue().toString());
                        chat.setYangkirim(ds.child("yangkirim").getValue().toString());
                        listClassChat.add(chat);
                    }
                }
                listClassChatFilter.add(listClassChat.get(listClassChat.size()-1));
                rv.setLayoutManager(new LinearLayoutManager(FragmentChatToko.this.getContext()));
                adapterChat=new AdapterChat(listClassChatFilter);
                rv.setAdapter(adapterChat);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
