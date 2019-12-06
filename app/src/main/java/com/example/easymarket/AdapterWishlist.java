package com.example.easymarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterWishlist extends RecyclerView.Adapter<AdapterWishlist.WishlistAdapter> {

    ArrayList<ClassWishlist> filterWishlist;
    ArrayList<ClassBarang> listClassBarang;
    onClickCallback click ;

    @NonNull
    @Override
    public AdapterWishlist.WishlistAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerwishlist,parent,false);
        return new AdapterWishlist.WishlistAdapter(view);
    }

    public AdapterWishlist(ArrayList<ClassWishlist> filterWishlist, ArrayList<ClassBarang> listClassBarang) {
        this.filterWishlist = filterWishlist;
        this.listClassBarang = listClassBarang;
    }

    public void setClick(onClickCallback click) {
        this.click = click;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterWishlist.WishlistAdapter holder, final int position) {
        holder.nama.setText(filterWishlist.get(position).getNamabarang());
        for (int i = 0; i < filterWishlist.size(); i++) {
            for (int j = 0; j < listClassBarang.size(); j++) {
                if(filterWishlist.get(i).namabarang.equals(listClassBarang.get(j).namabarang)){
//                    Glide.with(holder.itemView.getContext())
//                            .load(listClassBarang.get(j).fotoutama)
//                            .override(200,200)
//                            .into(holder.fotobarang);
                }
            }
        }
        holder.harga.setText(filterWishlist.get(position).getHargabarang());

        holder.pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClickCheckbox(listClassBarang.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterWishlist.size();
    }

    public class WishlistAdapter extends RecyclerView.ViewHolder {
        CheckBox pilih;
        ImageView fotobarang;
        TextView nama,harga,jumlah;
        Button tambah,kurang,hapus;

        public WishlistAdapter(@NonNull View itemView) {
            super(itemView);
            pilih=itemView.findViewById(R.id.cbPilih);
            fotobarang=itemView.findViewById(R.id.ivBarang);
            nama=itemView.findViewById(R.id.tvNamaBarang);
            harga=itemView.findViewById(R.id.tvHargaBarang);
            jumlah=itemView.findViewById(R.id.tvJumlah);
            tambah=itemView.findViewById(R.id.btnTambah);
            kurang=itemView.findViewById(R.id.btnKurang);
            hapus=itemView.findViewById(R.id.btnHapus);
            fotobarang=itemView.findViewById(R.id.ivBarang);


        }
    }
    public interface onClickCallback{
        void onClickCheckbox (ClassBarang b);
    }
}
