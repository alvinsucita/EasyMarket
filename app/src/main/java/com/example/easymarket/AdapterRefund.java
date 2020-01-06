package com.example.easymarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRefund extends RecyclerView.Adapter<AdapterRefund.ListViewHolder> {
    ArrayList<ClassNota> listClassNota;
    private static RVClickListener mylistener;

    public AdapterRefund(ArrayList<ClassNota> listClassNota, RVClickListener rvcl){
        this.listClassNota = listClassNota;
        mylistener=rvcl;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerrequestrefund,parent,false);
        return new AdapterRefund.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.judul.setText(listClassNota.get(position).idnota);
    }

    @Override
    public int getItemCount() {
        return listClassNota.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView judul;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            judul=itemView.findViewById(R.id.tvJudulRefund);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mylistener.recyclerViewListBarangClick(v, AdapterRefund.ListViewHolder.this.getLayoutPosition());
                }
            });
        }
    }
}
