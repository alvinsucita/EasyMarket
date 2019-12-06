package com.example.easymarket;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class AdapterMenuBarang extends RecyclerView.Adapter<AdapterMenuBarang.ListViewHolder> {
    ArrayList<ClassBarang> listClassBarang;
    private static RVClickListener mylistener;
    private int posisi;

    public AdapterMenuBarang(ArrayList<ClassBarang> listClassBarang, RVClickListener rvcl){
        this.listClassBarang = listClassBarang;
        mylistener=rvcl;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerbarang,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        String hargaasli = String.format("%,d", listClassBarang.get(position).harga);
        holder.nama.setText(listClassBarang.get(position).namabarang);
        holder.harga.setText("Rp. "+hargaasli);
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

    public AdapterMenuBarang(ArrayList<ClassBarang> listClassBarang) {
        this.listClassBarang = listClassBarang;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView fotobarang;
        TextView nama,harga;
        public ListViewHolder(@NonNull final View itemView) {
            super(itemView);
            fotobarang=itemView.findViewById(R.id.ivBarang);
            nama=itemView.findViewById(R.id.tvNamaBarang);
            harga=itemView.findViewById(R.id.tvHarga);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mylistener.recyclerViewListBarangClick(v,ListViewHolder.this.getLayoutPosition());
                }
            });
        }
    }
}
