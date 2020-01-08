package com.example.easymarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterChatToko extends RecyclerView.Adapter<AdapterChatToko.ListViewHolder> {
    ArrayList<String> namaOrang;
    private static RVClickListener mylistener;

    public AdapterChatToko( ArrayList<String> namaOrang,RVClickListener rvcl){
        this.namaOrang = namaOrang;
        mylistener=rvcl;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerchattoko,parent,false);
        return new AdapterChatToko.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        holder.nama.setText(namaOrang.get(position));
    }

    @Override
    public int getItemCount() {
        return namaOrang.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView nama ;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            nama=itemView.findViewById(R.id.tvNamaChatToko);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mylistener.recyclerViewListBarangClick(v, AdapterChatToko.ListViewHolder.this.getLayoutPosition());
                }
            });
        }
    }
}
