package com.example.easymarket;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class FragmentInfoBarang extends Fragment {

    public FragmentInfoBarang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_info_barang, container, false);
    }

    TextView isi,likes,dilihat,dibeli;
    Button btnLikes;
    public ArrayList<Barang> listBarang;
    public ArrayList<User> listUser;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final InfoBarang infoBarang = (InfoBarang) getActivity();
        listBarang = (ArrayList<Barang>) getArguments().getSerializable("listBarang");
        listUser = (ArrayList<User>) getArguments().getSerializable("listUser");

        isi=view.findViewById(R.id.tvIsiDeskripsi);
        likes=view.findViewById(R.id.tvLikesBarang);
        dilihat=view.findViewById(R.id.tvBarangDilihat);
        dibeli=view.findViewById(R.id.tvBarangTerjual);
        btnLikes=view.findViewById(R.id.btnLikes);

        likes.setText("");
        dilihat.setText("");
        dibeli.setText("");
        isi.setText("");

        likes.setText("Likes : "+listBarang.get(infoBarang.indeks).likes);
        dilihat.setText("Dilihat : "+listBarang.get(infoBarang.indeks).dilihat+" kali");
        dibeli.setText("Terjual : "+listBarang.get(infoBarang.indeks).dibeli+" kali");
        isi.setText(listBarang.get(infoBarang.indeks).deskripsi+"");

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setColor(Color.WHITE);
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setStroke(5, Color.BLACK);
        drawable4.setCornerRadius(50);
        isi.setBackground(drawable4);

        btnLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailuser ="";
                String idbarang = listBarang.get(infoBarang.indeks).idbarang;
                int ctr=0;

                for (int i = 0; i < listUser.size(); i++) {
//                    if(listUser.get(i).aktif.equals("1")){
//                        emailuser=listUser.get(i).email;
//                    }
                }

                if(infoBarang.listLikes.size()==0){
                    infoBarang.listLikes.add(new ClassLikes(idbarang,emailuser));
                    Toast.makeText(infoBarang, "Anda menyukai barang ini", Toast.LENGTH_SHORT).show();
                    listBarang.get(infoBarang.indeks).likes+=1;
                    likes.setText("Likes : "+listBarang.get(infoBarang.indeks).likes);
                }
                else{
                    for (int i = 0; i < infoBarang.listLikes.size(); i++) {
                        for (int j = 0; j < listUser.size(); j++) {
                            for (int k = 0; k < listBarang.size(); k++) {
                                if(infoBarang.listLikes.get(i).emailuser.equals(listUser.get(j).email) &&infoBarang.listLikes.get(i).idbarang.equals(listBarang.get(k).idbarang)){
                                    ctr++;
                                }
                            }
                        }
                    }
                    if(ctr==0){
                        infoBarang.listLikes.add(new ClassLikes(idbarang,emailuser));
                        Toast.makeText(infoBarang, "Anda menyukai barang ini", Toast.LENGTH_SHORT).show();
                        listBarang.get(infoBarang.indeks).likes+=1;
                        likes.setText("Likes : "+listBarang.get(infoBarang.indeks).likes);
                    }
                    else{
                        Toast.makeText(infoBarang, "Barang ini sudah anda sukai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
