package com.example.easymarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.listViewHolder> {
    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerchat,parent,false);
        return new AdapterChat.listViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class listViewHolder extends RecyclerView.ViewHolder {
        public listViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
