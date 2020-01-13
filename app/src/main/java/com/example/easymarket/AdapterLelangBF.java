package com.example.easymarket;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class AdapterLelangBF extends RecyclerView.Adapter<AdapterLelangBF.ListViewHolder> {
    ArrayList<_BarangBF> list_barang;
    OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onButtonClick(String position, View view);
    }

    public AdapterLelangBF(ArrayList<_BarangBF> list_barang) {
        this.list_barang = list_barang;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_lelang_biddingfee,parent,false);
        return new AdapterLelangBF.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        holder.tv_nama.setText("nama : " + list_barang.get(position).getNamaBrg() + "");
        holder.tv_harga.setText("harga retail : " + list_barang.get(position).getHarga_retail() + "");
        holder.tv_waktu.setText("sisa waktu : " + list_barang.get(position).getInit_date() + "");
        holder.tv_highestBid.setText("current highest bid : " + list_barang.get(position).getHighest_bid() + "");
    }

    @Override
    public int getItemCount() {
        return list_barang.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView item_picture;
        TextView tv_nama, tv_harga, tv_waktu, tv_highestBid, tv_owner;
        Button btn_setBid;
        public ListViewHolder(@NonNull View rview) {
            super(rview);
            item_picture = rview.findViewById(R.id.biddingFee_imageView);
            tv_nama = rview.findViewById(R.id.biddingFee_textView_namaBrg);
            tv_harga = rview.findViewById(R.id.biddingFee_textView_hrgRetail);
            tv_waktu = rview.findViewById(R.id.biddingFee_textView_sisaWaktu);
            tv_highestBid = rview.findViewById(R.id.biddingFee_textView_bidTertinggi);
            tv_owner = rview.findViewById(R.id.biddingFee_textView_owner);
            btn_setBid = rview.findViewById(R.id.biddingFee_button_placeBid);

            btn_setBid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onButtonClick(getAdapterPosition() + "", view);
                }
            });
        }
    }
}
