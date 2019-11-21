package com.example.easymarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterMenuBarang extends RecyclerView.Adapter<AdapterMenuBarang.ListViewHolder> {
    ArrayList<Barang> listBarang;
    private static RVClickListener mylistener;
    private int posisi;

    public AdapterMenuBarang(ArrayList<Barang> listBarang,RVClickListener rvcl){
        this.listBarang = listBarang;
        mylistener=rvcl;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerbarang,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        String hargaasli = String.format("%,d", listBarang.get(position).harga);
        holder.nama.setText(listBarang.get(position).namabarang);
        holder.harga.setText("Rp. "+hargaasli);
        holder.info.setText("Info");
        holder.fotobarang.setBackgroundResource(listBarang.get(position).foto);
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
        public ListViewHolder(@NonNull final View itemView) {
            super(itemView);
            fotobarang=itemView.findViewById(R.id.ivBarang);
            nama=itemView.findViewById(R.id.tvNamaBarang);
            harga=itemView.findViewById(R.id.tvHarga);
            info=itemView.findViewById(R.id.btnInfoBarang);
        }
    }
}
