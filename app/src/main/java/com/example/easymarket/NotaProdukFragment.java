package com.example.easymarket;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;

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
 * Activities that contain this fragment must implement the
 * {@link NotaProdukFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class NotaProdukFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public NotaProdukFragment() {
        // Required empty public constructor
    }

    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    TextView show;
    ImageView barang,barang2;
    TextView nama,jumlah,harga,nama2,jumlah2,harga2;
    ArrayList<ClassNota> listClassNota = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nota_produk, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nama=view.findViewById(R.id.tvNamaBarang3);
        jumlah=view.findViewById(R.id.tvJumlah3);
        harga=view.findViewById(R.id.tvHargaBarang3);
        barang=view.findViewById(R.id.ivBarang3);
        nama2=view.findViewById(R.id.tvNamaBarang4);
        jumlah2=view.findViewById(R.id.tvJumlah4);
        harga2=view.findViewById(R.id.tvHargaBarang4);
        barang2=view.findViewById(R.id.ivBarang4);

        FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
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
                FirebaseDatabase.getInstance().getReference().child("ClassNota").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Boolean cek = true;
                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                            ClassNota semua_Class_Nota =new ClassNota();
                            semua_Class_Nota.setIdnota((ds.child("idnota").getValue().toString()));
                            semua_Class_Nota.setNamatoko((ds.child("namatoko").getValue().toString()));
                            semua_Class_Nota.setIdbarang((ds.child("idbarang").getValue().toString()));
                            semua_Class_Nota.setNamauser(ds.child("namauser").getValue().toString());
                            semua_Class_Nota.setAlamat((ds.child("alamat").getValue().toString()));
                            semua_Class_Nota.setPembayaran((ds.child("pembayaran").getValue().toString()));
                            semua_Class_Nota.setJenispengiriman((ds.child("jenispengiriman").getValue().toString()));
                            semua_Class_Nota.setHargabarang(Integer.parseInt(ds.child("hargabarang").getValue().toString()));
                            semua_Class_Nota.setJumlahbarang(Integer.parseInt(ds.child("jumlahbarang").getValue().toString()));
                            semua_Class_Nota.setHargapengiriman(Integer.parseInt(ds.child("hargapengiriman").getValue().toString()));
                            semua_Class_Nota.setTotal(Integer.parseInt(ds.child("total").getValue().toString()));
                            listClassNota.add(semua_Class_Nota);
                        }
                        final NotaActivity notaActivity = (NotaActivity) getActivity();

                        if(notaActivity.cekbarang==1){
                            int totalsementara=0;
                            nama2.setVisibility(View.VISIBLE);
                            harga2.setVisibility(View.VISIBLE);
                            jumlah2.setVisibility(View.VISIBLE);
                            barang2.setVisibility(View.VISIBLE);

                            for (int i = 0; i < listClassBarang.size(); i++) {
                                if(listClassNota.get(listClassNota.size()-1).idbarang.equals(listClassBarang.get(i).idbarang)){
                                    nama2.setText(listClassBarang.get(i).namabarang);
                                }
                                else if(listClassNota.get(listClassNota.size()-2).idbarang.equals(listClassBarang.get(i).idbarang)){
                                    nama.setText(listClassBarang.get(i).namabarang);
                                }
                            }
                            String hargaasli = String.format("%,d", listClassNota.get(listClassNota.size()-1).hargabarang);
                            String hargaasli2 = String.format("%,d", listClassNota.get(listClassNota.size()-2).hargabarang);

                            jumlah.setText("Jumlah barang : "+listClassNota.get(listClassNota.size()-2).jumlahbarang+"");
                            harga.setText("Rp. "+hargaasli2);
                            FirebaseStorage.getInstance().getReference().child("GambarBarang").child(listClassNota.get(listClassNota.size()-2).idbarang).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(getContext()).load(uri).into(barang);
                                }
                            }) ;

                            jumlah2.setText("Jumlah barang : "+listClassNota.get(listClassNota.size()-1).jumlahbarang+"");
                            harga2.setText("Rp. "+hargaasli);
                            FirebaseStorage.getInstance().getReference().child("GambarBarang").child(listClassNota.get(listClassNota.size()-1).idbarang).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(getContext()).load(uri).into(barang2);
                                }
                            }) ;
                        }
                        else{
                            for (int i = 0; i < listClassBarang.size(); i++) {
                                if(listClassNota.get(listClassNota.size()-1).idbarang.equals(listClassBarang.get(i).idbarang)){
                                    nama.setText(listClassBarang.get(i).namabarang);
                                }
                            }
                            String hargaasli = String.format("%,d", listClassNota.get(listClassNota.size()-1).hargabarang);

                            jumlah.setText("Jumlah barang : "+listClassNota.get(listClassNota.size()-1).jumlahbarang+"");
                            harga.setText("Rp. "+hargaasli);
                            FirebaseStorage.getInstance().getReference().child("GambarBarang").child(listClassNota.get(listClassNota.size()-1).idbarang).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(getContext()).load(uri).into(barang);
                                }
                            }) ;

                            nama2.setVisibility(View.INVISIBLE);
                            harga2.setVisibility(View.INVISIBLE);
                            jumlah2.setVisibility(View.INVISIBLE);
                            barang2.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
