package com.example.easymarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.CommentViewHolder> {
    ArrayList<ClassComment>filterComment;

    public AdapterComment(ArrayList<ClassComment> listComment) {
        this.filterComment = listComment;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclercomment,parent,false);
        CommentViewHolder holder = new CommentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.nama.setText(filterComment.get(position).nama);
        holder.isi.setText(filterComment.get(position).isi);
    }



    @Override
    public int getItemCount() {
        return filterComment.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView nama,isi;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            foto=itemView.findViewById(R.id.ivFoto);
            nama=itemView.findViewById(R.id.tvNama);
            isi=itemView.findViewById(R.id.tvIsiComment);

        }
    }
}
