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
    public ArrayList<Barang> listBarang;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        InfoBarang infoBarang = (InfoBarang) getActivity();
        listBarang = (ArrayList<Barang>) getArguments().getSerializable("listBarang");

        isi=view.findViewById(R.id.tvIsiDeskripsi);
        likes=view.findViewById(R.id.tvLikesBarang);
        dilihat=view.findViewById(R.id.tvBarangDilihat);
        dibeli=view.findViewById(R.id.tvBarangTerjual);

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
        drawable4.setCornerRadius(100);
        isi.setBackground(drawable4);
    }
}
