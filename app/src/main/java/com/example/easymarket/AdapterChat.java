package com.example.easymarket;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.listViewHolder> {
    ArrayList<ClassChat> listClassChat;
    private static RVClickListener mylistener;

    public AdapterChat( ArrayList<ClassChat> listClassChat){
        this.listClassChat = listClassChat;
    }

    public AdapterChat( ArrayList<ClassChat> listClassChat,RVClickListener rvcl){
        this.listClassChat = listClassChat;
        mylistener=rvcl;
    }
    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerchat,parent,false);
        return new AdapterChat.listViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, int position) {
        if(FirebaseAuth.getInstance().getCurrentUser().getEmail().equals(listClassChat.get(position).yangkirim)){
            holder.nama.setText("Anda");
            holder.tanggal.setText(listClassChat.get(position).waktu);
            holder.isi.setText(listClassChat.get(position).isi);
            holder.isi.setTextAlignment(holder.isi.TEXT_ALIGNMENT_VIEW_END);
        }
        else{
            holder.nama.setText(listClassChat.get(position).yangkirim);
            holder.tanggal.setText(listClassChat.get(position).waktu);
            holder.isi.setText(listClassChat.get(position).isi);
            holder.isi.setTextAlignment(holder.isi.TEXT_ALIGNMENT_VIEW_START);
        }
    }

    @Override
    public int getItemCount() {
        return listClassChat.size();
    }

    public class listViewHolder extends RecyclerView.ViewHolder {
        TextView nama,isi,tanggal;
        public listViewHolder(@NonNull View itemView) {
            super(itemView);
            nama=itemView.findViewById(R.id.tvnamauser);
            isi=itemView.findViewById(R.id.tvchat);
            tanggal=itemView.findViewById(R.id.tvtanggalchat);

            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(100);
            drawable.setStroke(8, Color.LTGRAY);
            drawable.setColor(Color.WHITE);
            isi.setBackground(drawable);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mylistener.recyclerViewListBarangClick(v, AdapterChat.listViewHolder.this.getLayoutPosition());
                }
            });
        }
    }
}
