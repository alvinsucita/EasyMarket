package com.example.easymarket;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTambahBarang extends Fragment {

    Spinner sp;
    EditText namabarang,harga,stok,deskripsi;
    Button add,carifoto;
    ImageView foto;
    Uri selected;
    ArrayList<ClassBarang>listClassBarang = new ArrayList<>();
    DatabaseReference databaseReference;
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && data!=null){
            selected = data.getData();
            foto.setBackgroundResource(0);
            foto.setImageURI(selected);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final HomeToko homeToko = (HomeToko) getActivity();

        sp=view.findViewById(R.id.spKategori);
        namabarang=view.findViewById(R.id.etNamaBarang);
        harga=view.findViewById(R.id.etHargaBarang);
        deskripsi=view.findViewById(R.id.etDeskripsi);
        stok=view.findViewById(R.id.etStokBarang);
        add=view.findViewById(R.id.btnAdd);
        carifoto=view.findViewById(R.id.btnFoto);
        foto=view.findViewById(R.id.ivFotoBarang);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.WHITE);
        namabarang.setBackground(drawable);
        harga.setBackground(drawable);
        stok.setBackground(drawable);

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
        add.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setCornerRadius(100);
        drawable4.setColor(Color.WHITE);
        deskripsi.setBackground(drawable4);

        ArrayList<String> listspinner = new ArrayList<>();
        listspinner.add("Pakaian");
        listspinner.add("Ibu dan Anak");
        listspinner.add("Elektronik");
        listspinner.add("Makanan & Minuman");
        listspinner.add("Games");
        listspinner.add("Kecantikan");
        listspinner.add("Olahraga");
        listspinner.add("Lain-lain");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassBarang");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listspinner);
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

                databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassBarang");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Boolean cek = true;
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
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                for (int i = 0; i < listClassBarang.size(); i++) {
                    if(listClassBarang.get(i).kategori.equals(strkategori)){
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
                    if(selected!=null){
                        FirebaseStorage.getInstance().getReference().child("GambarBarang/"+ strid).putFile(selected);
                        ClassBarang barangbaru=new ClassBarang(strid,((HomeToko) getActivity()).tokologin,strnama,strdeskripsi,strkategori,Integer.parseInt(strharga),0,0,0,Integer.parseInt(strstok));
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassBarang");
                        String key=databaseReference.push().getKey();
                        databaseReference.child(key).setValue(barangbaru).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getContext(), "Barang baru berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                            }
                        });

                        namabarang.setText("");
                        harga.setText("");
                        deskripsi.setText("");
                        stok.setText("");
                        foto.setBackgroundResource(0);
                    }
                    else{
                        Toast.makeText(homeToko, "Masukkan foto barang", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(homeToko, "Isi semua field terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        carifoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(change,1);
            }
        });


    }
}
