package com.example.easymarket;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class AdapterBarangLelang extends RecyclerView.Adapter<AdapterBarangLelang.ListViewHolder> {
    ArrayList<ClassBarang> listClassBarang;
    private static RVClickListener mylistener;
    private int posisi;

    public AdapterBarangLelang(ArrayList<ClassBarang> listClassBarang, RVClickListener rvcl){
        this.listClassBarang = listClassBarang;
        mylistener=rvcl;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_barang_lelang,parent,false);
        return new AdapterBarangLelang.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        String hargaasli = String.format("%,d", listClassBarang.get(position).harga);
        holder.nama.setText(listClassBarang.get(position).namabarang);
        holder.harga.setText("Harga asli : "+"Rp. "+hargaasli);
        FirebaseStorage.getInstance().getReference().child("GambarBarang").child(listClassBarang.get(position).idbarang).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(holder.itemView.getContext()).load(uri).into(holder.fotobarang);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listClassBarang.size();
    }

    public AdapterBarangLelang(ArrayList<ClassBarang> listClassBarang) {
        this.listClassBarang = listClassBarang;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        ImageView fotobarang;
        TextView nama,harga;
        public ListViewHolder(@NonNull final View itemView) {
            super(itemView);
            fotobarang=itemView.findViewById(R.id.ivBarangLelang);
            nama=itemView.findViewById(R.id.tvNamaBarangLelang);
            harga=itemView.findViewById(R.id.tvHargaLelang);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mylistener.recyclerViewListBarangClick(v, AdapterBarangLelang.ListViewHolder.this.getLayoutPosition());
                }
            });
        }
    }
}
