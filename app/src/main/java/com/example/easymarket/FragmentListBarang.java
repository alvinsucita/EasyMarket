package com.example.easymarket;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListBarang extends Fragment {


    public ArrayList<ClassBarang> listClassBarang= new ArrayList<>();
    public ArrayList<ClassBarang> filterBarang= new ArrayList<>();
    public ArrayList<ClassRequestLelang> requestLelang= new ArrayList<>();
    Spinner sp;
    TextView nama,harga,kategori,likes,terjual,lihat,deskripsi,stok;
    Button request;
    String strnama="",strkategori="",strdeskripsi="",idbarang="";
    int intharga=0,intstok=0,intlihat=0,intterjual=0,intlikes=0;
    ImageView foto;

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
        listClassBarang = (ArrayList<ClassBarang>) getArguments().getSerializable("listClassBarang");
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
        foto=view.findViewById(R.id.ivFoto);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setStroke(8, Color.LTGRAY);
        drawable2.setColor(Color.WHITE);
        sp.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setColor(Color.BLACK);
        request.setBackground(drawable3);

        FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                listClassBarang.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassBarang semua_Class_barang =new ClassBarang();
                    semua_Class_barang.setDeskripsi(ds.child("deskripsi").getValue().toString());
                    semua_Class_barang.setDibeli(Integer.parseInt(ds.child("dibeli").getValue().toString()));
                    semua_Class_barang.setDilihat(Integer.parseInt(ds.child("dilihat").getValue().toString()));
                    semua_Class_barang.setHarga(Integer.parseInt(ds.child("harga").getValue().toString()));
                    semua_Class_barang.setIdbarang(ds.child("idbarang").getValue().toString());
                    semua_Class_barang.setKategori(ds.child("kategori").getValue().toString());
                    semua_Class_barang.setLikes(Integer.parseInt(ds.child("likes").getValue().toString()));
                    semua_Class_barang.setNamabarang(ds.child("namabarang").getValue().toString());
                    semua_Class_barang.setNamatoko(ds.child("namatoko").getValue().toString());
                    semua_Class_barang.setStok(Integer.parseInt(ds.child("stok").getValue().toString()));
                    listClassBarang.add(semua_Class_barang);
                }
                for (int i = 0; i < listClassBarang.size(); i++) {
                    if(listClassBarang.get(i).namatoko.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        filterBarang.add(listClassBarang.get(i));
                    }
                }

                ArrayList<String> listspinner = new ArrayList<>();
                for (int i = 0; i < filterBarang.size(); i++) {
                    listspinner.add(filterBarang.get(i).namabarang);
                }

                if(listspinner.size()!=0){
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listspinner);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp.setAdapter(dataAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strnama=sp.getSelectedItem().toString();
                for (int i = 0; i < filterBarang.size(); i++) {
                    if(filterBarang.get(i).namabarang.equals(strnama)){
                        intharga= filterBarang.get(i).harga;
                        strdeskripsi= filterBarang.get(i).deskripsi;
                        strkategori=filterBarang.get(i).kategori;
                        intlikes= filterBarang.get(i).likes;
                        intlihat= filterBarang.get(i).dilihat;
                        intstok= filterBarang.get(i).stok;
                        intterjual= filterBarang.get(i).dibeli;
                        idbarang = filterBarang.get(i).idbarang;
                    }
                }
                nama.setText("Nama : "+strnama);
                String hargaasli = String.format("%,d",intharga);
                harga.setText("Harga : Rp."+hargaasli+"/pcs");
                deskripsi.setText("Deskripsi : "+strdeskripsi);
                kategori.setText("Kategori : "+strkategori);
                likes.setText("Likes : "+intlikes+"");
                lihat.setText("Dilihat : "+intlihat+" kali");
                stok.setText("Stok : "+intstok+"pcs");
                terjual.setText("Barang Terjual : "+intterjual+" kali");
                FirebaseStorage.getInstance().getReference().child("GambarBarang").child(idbarang).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(getContext()).load(uri).into(foto);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String id="";
//                int ctr=0;
//                for (int i = 0; i < listClassBarang.size(); i++) {
//                    if(strnama.equals(listClassBarang.get(i).namabarang)){
//                        id= listClassBarang.get(i).idbarang;
//                    }
//                }
//                for (int i = 0; i < ((HomeToko) getActivity()).listRequestLelang.size(); i++) {
//                    if(id.equals(((HomeToko) getActivity()).listRequestLelang.get(i).idbarang)){
//                        ctr++;
//                    }
//                }
//                if(ctr==0){
//                    ((HomeToko) getActivity()).listRequestLelang.add(new ClassRequestLelang(id));
//                    Toast.makeText(getContext(), ((HomeToko) getActivity()).listRequestLelang.size()+"", Toast.LENGTH_SHORT).show();
//                }
//                else if(ctr>0){
//                    Toast.makeText(getContext(), "ClassBarang sudah direquest", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}