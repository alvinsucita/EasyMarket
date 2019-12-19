package com.example.easymarket;

import android.text.style.IconMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDitunda extends RecyclerView.Adapter<AdapterDitunda.ListViewHolder> {
    ArrayList<ClassNota> listClassNota;
    private static RVClickListener mylistener;

    public AdapterDitunda(ArrayList<ClassNota> listClassNota, RVClickListener rvcl){
        this.listClassNota = listClassNota;
        mylistener=rvcl;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerditunda,parent,false);
        return new AdapterDitunda.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

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
            foto=itemView.findViewById(R.id.ivbarangditunda);
            nama=itemView.findViewById(R.id.tvnamabarangditunda);
            jumlah=itemView.findViewById(R.id.tvjumlahbarangditunda);
            total=itemView.findViewById(R.id.tvtotalbarangditunda);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mylistener.recyclerViewListBarangClick(v, AdapterDitunda.ListViewHolder.this.getLayoutPosition());
                }
            });
        }
    }
}
