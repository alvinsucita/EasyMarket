package com.example.easymarket;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.EventViewHolder> {
    ArrayList<ClassEvent> listClassEvent;
    private static RVClickListener mylistener;

    public AdapterEvent(ArrayList<ClassEvent> listClassEvent, RVClickListener rvcl){
        this.listClassEvent = listClassEvent;
        mylistener=rvcl;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerevent,parent,false);
        return new AdapterEvent.EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.judul.setText(listClassEvent.get(position).judul);
    }


    @Override
    public int getItemCount() {
        return listClassEvent.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{
        TextView judul;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            judul=itemView.findViewById(R.id.tvJudulEvent);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mylistener.recyclerViewListBarangClick(v, AdapterEvent.EventViewHolder.this.getLayoutPosition());
                }
            });
        }
    }
}
