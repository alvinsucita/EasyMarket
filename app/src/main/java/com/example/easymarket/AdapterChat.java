package com.example.easymarket;

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

    public AdapterChat( ArrayList<ClassChat> listClassChat){
        this.listClassChat = listClassChat;
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
            holder.nama.setText("Anda"+" "+listClassChat.get(position).waktu);;
            holder.isi.setText(listClassChat.get(position).isi);
            holder.isi.setTextAlignment(holder.isi.TEXT_ALIGNMENT_VIEW_END);
        }
        else{
            holder.nama.setText(listClassChat.get(position).yangkirim+" "+listClassChat.get(position).waktu);
            holder.isi.setText(listClassChat.get(position).isi);
            holder.isi.setTextAlignment(holder.isi.TEXT_ALIGNMENT_VIEW_START);
        }
    }

    @Override
    public int getItemCount() {
        return listClassChat.size();
    }

    public class listViewHolder extends RecyclerView.ViewHolder {
        TextView nama,isi;
        public listViewHolder(@NonNull View itemView) {
            super(itemView);
            nama=itemView.findViewById(R.id.tvnamauser);
            isi=itemView.findViewById(R.id.tvchat);
        }
    }
}
