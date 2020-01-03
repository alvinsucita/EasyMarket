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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class AdapterMenunggu extends RecyclerView.Adapter<AdapterMenunggu.ListViewHolder> {
    ArrayList<ClassNota> listClassNota;
    ArrayList<ClassBarang> listClassBarang;
    private static RVClickListener mylistener;

    public AdapterMenunggu(ArrayList<ClassNota> listClassNota,ArrayList<ClassBarang> listClassBarang, RVClickListener rvcl){
        this.listClassNota = listClassNota;
        this.listClassBarang = listClassBarang;
        mylistener=rvcl;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclermenunggu,parent,false);
        return new AdapterMenunggu.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        String hargaasli = String.format("%,d", listClassNota.get(position).total);
        holder.nama.setText(listClassBarang.get(position).namabarang);
        holder.jumlah.setText("Jumlah barang "+listClassNota.get(position).jumlahbarang);
        holder.total.setText("Rp. "+hargaasli);
        FirebaseStorage.getInstance().getReference().child("GambarBarang").child(listClassNota.get(position).idbarang).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(holder.itemView.getContext()).load(uri).into(holder.foto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listClassNota.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView nama,jumlah,total;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            foto=itemView.findViewById(R.id.ivbarangmenunggu);
            nama=itemView.findViewById(R.id.tvnamabarangmenunggu);
            jumlah=itemView.findViewById(R.id.tvjumlahbarangmenunggu);
            total=itemView.findViewById(R.id.tvtotalbarangmenunggu);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mylistener.recyclerViewListBarangClick(v, AdapterMenunggu.ListViewHolder.this.getLayoutPosition());
                }
            });
        }
    }
}
