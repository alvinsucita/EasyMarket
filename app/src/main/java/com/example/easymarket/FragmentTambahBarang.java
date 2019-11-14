package com.example.easymarket;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTambahBarang extends Fragment {

    public ArrayList<Barang> listBarang;
    Spinner sp;
    EditText namabarang,harga,stok,deskripsi;
    Button add;
    public FragmentTambahBarang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tambah_barang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final HomeToko homeToko = (HomeToko) getActivity();

        listBarang = (ArrayList<Barang>) getArguments().getSerializable("listBarang");
        sp=view.findViewById(R.id.spKategori);
        namabarang=view.findViewById(R.id.etNamaBarang);
        harga=view.findViewById(R.id.etHargaBarang);
        deskripsi=view.findViewById(R.id.etDeskripsi);
        stok=view.findViewById(R.id.etStokBarang);
        add=view.findViewById(R.id.btnAdd);
        ArrayList<String> listspinner = new ArrayList<>();
        listspinner.add("Pakaian");
        listspinner.add("Ibu dan Anak");
        listspinner.add("Elektronik");
        listspinner.add("Makanan & Minuman");
        listspinner.add("Games");
        listspinner.add("Kecantikan");
        listspinner.add("Olahraga");
        listspinner.add("Lain-lain");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listspinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strnama = namabarang.getText().toString();
                String strharga = harga.getText().toString();
                String strdeskripsi = deskripsi.getText().toString();
                String strstok = stok.getText().toString();
                String strkategori = sp.getSelectedItem().toString();
                String strid = "";
                int ctr=0;
                for (int i = 0; i < listBarang.size(); i++) {
                    if(listBarang.get(i).kategori.equals(strkategori)){
                        ctr++;
                    }
                }

                if(ctr>=0&&ctr<9){
                    strid=strkategori.toUpperCase().substring(0,1)+strkategori.toUpperCase().substring(1,2)+"0000"+(ctr+1);
                }else if(ctr>=9&&ctr<99){
                    strid=strkategori.toUpperCase().substring(0,1)+strkategori.toUpperCase().substring(1,2)+"000"+(ctr+1);
                }else if(ctr>=99&&ctr<999){
                    strid=strkategori.toUpperCase().substring(0,1)+strkategori.toUpperCase().substring(1,2)+"00"+(ctr+1);
                }else if(ctr>=999&&ctr<9999){
                    strid=strkategori.toUpperCase().substring(0,1)+strkategori.toUpperCase().substring(1,2)+"0"+(ctr+1);
                }else if(ctr>=9999&&ctr<99999){
                    strid=strkategori.toUpperCase().substring(0,1)+strkategori.toUpperCase().substring(1,2)+(ctr+1);
                }

                if(!strnama.equals("") && !strharga.equals("") && !strdeskripsi.equals("") && !strstok.equals("")){
                    listBarang.add(new Barang(strid,homeToko.yanglogin,strnama,strdeskripsi,strkategori,Integer.parseInt(strharga),0,0,0,Integer.parseInt(strstok)));
                    Toast.makeText(getContext(), "Barang berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    namabarang.setText("");
                    harga.setText("");
                    deskripsi.setText("");
                    stok.setText("");
                }
            }
        });
    }
}
