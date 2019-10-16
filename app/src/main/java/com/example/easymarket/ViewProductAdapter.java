package com.example.easymarket;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewProductAdapter extends RecyclerView.Adapter<ViewProductAdapter.ProductViewHolder> {

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView namabarang,hargabarang,ratingbarang;
        public ProductViewHolder(@NonNull View view) {
            super(view);
            iv = itemView.findViewById(R.id.ivppic);
            namabarang = itemView.findViewById(R.id.tvpname);
            hargabarang = itemView.findViewById(R.id.tvpprice);
            ratingbarang = itemView.findViewById(R.id.tvprate);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setStroke(5, Color.BLACK);
            drawable.setCornerRadius(15);
            namabarang.setBackground(drawable);
            hargabarang.setBackground(drawable);
            ratingbarang.setBackground(drawable);
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewproducts,parent,false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
