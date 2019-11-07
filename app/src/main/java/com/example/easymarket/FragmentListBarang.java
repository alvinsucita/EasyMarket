package com.example.easymarket;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListBarang extends Fragment {


    public ArrayList<Barang> listBarang;
    Spinner sp;
    TextView nama,harga,kategori,likes,terjual,lihat,deskripsi,stok;
    Button request;

    public FragmentListBarang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_list_barang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HomeToko homeToko = (HomeToko) getActivity();
        listBarang = (ArrayList<Barang>) getArguments().getSerializable("listBarang");
        sp=view.findViewById(R.id.spListBarang);
        nama=view.findViewById(R.id.tvNamaBarang);
        harga=view.findViewById(R.id.tvHargaBarang);
        kategori=view.findViewById(R.id.tvKategoriBarang);
        deskripsi=view.findViewById(R.id.tvDeskripsiBarang);
        stok=view.findViewById(R.id.tvStok);
        lihat=view.findViewById(R.id.tvDilihat);
        terjual=view.findViewById(R.id.tvTerjual);
        likes=view.findViewById(R.id.tvLikes);
        request=view.findViewById(R.id.btnRequest);
        ArrayList<String> listspinner = new ArrayList<>();
        for (int i = 0; i < listBarang.size(); i++) {
            if(listBarang.get(i).namatoko.equals(homeToko.yanglogin)){
                listspinner.add(listBarang.get(i).namabarang);
            }
        }
        if(listspinner.size()!=0){
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listspinner);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(dataAdapter);
        }

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strnama="",strkategori="",strdeskripsi="";
                int intharga=0,intstok=0,intlihat=0,intterjual=0,intlikes=0;
                strnama=sp.getSelectedItem().toString();
                for (int i = 0; i < listBarang.size(); i++) {
                    if(listBarang.get(i).namabarang.equals(strnama)){
                        intharga=listBarang.get(i).harga;
                        strdeskripsi=listBarang.get(i).deskripsi;
                        strkategori=listBarang.get(i).kategori;
                        intlikes=listBarang.get(i).likes;
                        intlihat=listBarang.get(i).dilihat;
                        intstok=listBarang.get(i).stok;
                        intterjual=listBarang.get(i).dibeli;
                    }
                }
                nama.setText("Nama : "+strnama);
                harga.setText("Harga : Rp."+intharga+"/pcs");
                deskripsi.setText("Deskripsi : "+strdeskripsi);
                kategori.setText("Kategori : "+strkategori);
                likes.setText("Likes : "+intlikes+"");
                lihat.setText("Dilihat : "+intlihat+"kali");
                stok.setText("Stok : "+intstok+"pcs");
                terjual.setText("Barang Terjual : "+intterjual+"kali");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}