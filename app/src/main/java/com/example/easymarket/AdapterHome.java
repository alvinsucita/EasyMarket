package com.example.easymarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<Adapter.HomeViewHolder> {

    private static RVClickListener mylistener;
    private ArrayList<Barang> arrBarang;
    private int posisi;

    public AdapterHome(ArrayList<Barang> arrBarang,RVClickListener rvcl){
        this.arrBarang = arrBarang;
        mylistener = rvcl;
    }

    public int getPosisi() {
        return posisi;
    }

    public void setPosisi(int posisi) {
        this.posisi = posisi;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View v = inf.inflate(R.layout.content_barang,parent,false);
        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        int kembali = 0;
        if (arrBarang != null){
            kembali = arrBarang.size();
        }
        return kembali;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{

        ImageView img1,img2;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.image2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mylistener.recyclerViewListClick(view,HomeViewHolder.this.getLayoutPosition());
                }
            });
        }
    }

}
