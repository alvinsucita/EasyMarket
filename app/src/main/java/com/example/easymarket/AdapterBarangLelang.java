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
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.nama.setText(listClassBarang.get(position).namabarang);
        holder.harga.setText("Last Bid : "+ listClassBarang.get(position).harga);
        holder.info.setText("Info");
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        Button info;
        public ListViewHolder(@NonNull final View itemView) {
            super(itemView);
            fotobarang=itemView.findViewById(R.id.ivBarangLelang);
            nama=itemView.findViewById(R.id.tvNamaBarangLelang);
            harga=itemView.findViewById(R.id.tvHargaLelang);
            info=itemView.findViewById(R.id.btnInfoBarangLelang);

        }
    }
}
