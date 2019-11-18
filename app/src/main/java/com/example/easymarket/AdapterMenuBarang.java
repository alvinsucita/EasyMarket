package com.example.easymarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMenuBarang extends RecyclerView.Adapter<AdapterMenuBarang.ListViewHolder> {
    ArrayList<Barang> listBarang;

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerbarang,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.nama.setText(listBarang.get(position).namabarang);
        holder.harga.setText(listBarang.get(position).harga+"");
        holder.info.setText("Info");
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    public AdapterMenuBarang(ArrayList<Barang> listBarang) {
        this.listBarang = listBarang;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView fotobarang;
        TextView nama,harga;
        Button info;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            fotobarang=itemView.findViewById(R.id.ivBarang);
            nama=itemView.findViewById(R.id.tvNamaBarang);
            harga=itemView.findViewById(R.id.tvHarga);
            info=itemView.findViewById(R.id.btnInfoBarang);
        }
    }
}
