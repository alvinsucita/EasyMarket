package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WishList extends AppCompatActivity {
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    ArrayList<ClassNota> listNota = new ArrayList<>();
    CheckBox pilih1,pilih2;
    TextView nama1,nama2,harga1,harga2,jumlah1,jumlah2,total;
    Button tambah1,tambah2,kurang1,kurang2,hapus1,hapus2,beli;
    ImageView gambar1,gambar2;
    int jumlahtotal,jumlahbarang1=0,jumlahbarang2=0,hargabarang1=0,hargabarang2=0;
    int beli1=0,beli2=0;
    String userwish="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        pilih1=findViewById(R.id.cbPilih1);
        pilih2=findViewById(R.id.cbPilih2);
        nama1=findViewById(R.id.tvBarang1);
        nama2=findViewById(R.id.tvBarang2);
        harga1=findViewById(R.id.tvHarga1);
        harga2=findViewById(R.id.tvHarga2);
        jumlah1=findViewById(R.id.tvJumlah1);
        jumlah2=findViewById(R.id.tvJumlah2);
        total=findViewById(R.id.tvTotal);
        tambah1=findViewById(R.id.btnTambah1);
        tambah2=findViewById(R.id.btnTambah2);
        kurang1=findViewById(R.id.btnKurang1);
        kurang2=findViewById(R.id.btnKurang2);
        hapus1=findViewById(R.id.btnHapus1);
        hapus2=findViewById(R.id.btnHapus2);
        beli=findViewById(R.id.btnBeli);
        gambar1=findViewById(R.id.ivBarang1);
        gambar2=findViewById(R.id.ivBarang2);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setColor(Color.WHITE);
        drawable4.setShape(GradientDrawable.OVAL);
        drawable4.setStroke(5, Color.BLACK);
        beli.setBackground(drawable4);

        total.setText("Total Harga : ");

        Intent i = getIntent();
        listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
        listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
        listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
        listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");

        for (int j = 0; j < listUser.size(); j++) {
            if (listUser.get(j).aktif.equals("1")) {
                userwish=listUser.get(j).nama;
            }
        }
        for (int j = 0; j < listWishlist.size(); j++) {
            if(listWishlist.get(j).yangbeli.equals(userwish)){
                if(nama1.getText().toString().equals("kosong")){
                    pilih1.setVisibility(View.VISIBLE);
                    gambar1.setVisibility(View.VISIBLE);
                    nama1.setVisibility(View.VISIBLE);
                    nama1.setText(listWishlist.get(j).namabarang);
                    tambah1.setVisibility(View.VISIBLE);
                    kurang1.setVisibility(View.VISIBLE);
                    hapus1.setVisibility(View.VISIBLE);
                    jumlah1.setVisibility(View.VISIBLE);
                    jumlah1.setVisibility(View.VISIBLE);
                    harga1.setVisibility(View.VISIBLE);
                    for (int k = 0; k < listBarang.size(); k++) {
                        if(listWishlist.get(j).namabarang.equals(listBarang.get(k).namabarang)){
                            hargabarang1=listBarang.get(k).harga;
                            harga1.setText("Rp. "+hargabarang1);
                        }
                    }
                }
                else if(nama2.getText().toString().equals("kosong")){
                    pilih2.setVisibility(View.VISIBLE);
                    gambar2.setVisibility(View.VISIBLE);
                    nama2.setVisibility(View.VISIBLE);
                    nama2.setText(listWishlist.get(j).namabarang);
                    tambah2.setVisibility(View.VISIBLE);
                    kurang2.setVisibility(View.VISIBLE);
                    hapus2.setVisibility(View.VISIBLE);
                    jumlah2.setVisibility(View.VISIBLE);
                    jumlah2.setVisibility(View.VISIBLE);
                    harga2.setVisibility(View.VISIBLE);
                    for (int k = 0; k < listBarang.size(); k++) {
                        if(listWishlist.get(j).namabarang.equals(listBarang.get(k).namabarang)){
                            hargabarang2=listBarang.get(k).harga;
                            harga2.setText("Rp. "+hargabarang2);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void toDetailAlamat(View view) {
        if(beli1==1 && beli2==1){
            Intent i = new Intent(WishList.this,DetailAlamat.class);
            i.putExtra("subtotal",jumlahtotal);
            //LEMPAR DATA KE DETAIL ALAMAT
            startActivity(i);
        }
        else if(beli1==1){

        }
        else if(beli2==1){

        }
    }

    public void tambah1(View view) {
        jumlahbarang1++;
        jumlah1.setText(jumlahbarang1+"");
        if(beli1==1){
            if(beli2==1){
                jumlahtotal=0;
                jumlahtotal=hargabarang1*jumlahbarang1+hargabarang2*jumlahbarang2;
                total.setText("Total : Rp. "+jumlahtotal);
            }
            else{
                jumlahtotal=0;
                jumlahtotal=hargabarang1*jumlahbarang1;
                total.setText("Total : Rp. "+jumlahtotal);
            }
        }
    }

    public void kurang1(View view) {
        if(jumlahbarang1>0){
            jumlahbarang1--;
            jumlah1.setText(jumlahbarang1+"");
            if(beli1==1){
                if(beli2==1){
                    jumlahtotal=0;
                    jumlahtotal=hargabarang1*jumlahbarang1+hargabarang2*jumlahbarang2;
                    total.setText("Total : Rp. "+jumlahtotal);
                }
                else{
                    jumlahtotal=0;
                    jumlahtotal=hargabarang1*jumlahbarang1;
                    total.setText("Total : Rp. "+jumlahtotal);
                }
            }
        }
    }

    public void hapus1(View view) {
        if(nama2.getText().toString().equals("kosong")){
            pilih1.setVisibility(View.INVISIBLE);
            gambar1.setVisibility(View.INVISIBLE);
            nama1.setVisibility(View.INVISIBLE);
            tambah1.setVisibility(View.INVISIBLE);
            kurang1.setVisibility(View.INVISIBLE);
            hapus1.setVisibility(View.INVISIBLE);
            jumlah1.setVisibility(View.INVISIBLE);
            harga1.setVisibility(View.INVISIBLE);
            beli1=0;
            jumlahtotal=jumlahtotal-hargabarang1*jumlahbarang1;
            total.setText("Total : Rp. "+jumlahtotal);
            for (int i = 0; i < listWishlist.size(); i++) {
                if(listWishlist.get(i).namabarang.equals(nama1.getText().toString())&&listWishlist.get(i).yangbeli.equals(userwish)){
                    listWishlist.remove(i);
                }
            }
        }
        else{
            for (int i = 0; i < listWishlist.size(); i++) {
                if(listWishlist.get(i).namabarang.equals(nama1.getText().toString())&&listWishlist.get(i).yangbeli.equals(userwish)){
                    listWishlist.remove(i);
                }
            }
            beli1=beli2;
            nama1.setText(nama2.getText().toString());
            jumlah1.setText(jumlah2.getText().toString());
            harga1.setText(harga2.getText().toString());
            hargabarang1=hargabarang2;
            jumlahbarang1=jumlahbarang2;
            hargabarang2=0;
            beli2=0;
            jumlahbarang2=0;
            if(pilih2.isChecked()){
                pilih1.setChecked(true);
                jumlahtotal=0;
                jumlahtotal=hargabarang1*jumlahbarang1+hargabarang2*jumlahbarang2;
                total.setText("Total : Rp. "+jumlahtotal);
            }
            else{
                jumlahtotal=0;
                total.setText("Total : Rp. "+jumlahtotal);
            }
            pilih2.setVisibility(View.INVISIBLE);
            gambar2.setVisibility(View.INVISIBLE);
            nama2.setVisibility(View.INVISIBLE);
            tambah2.setVisibility(View.INVISIBLE);
            kurang2.setVisibility(View.INVISIBLE);
            hapus2.setVisibility(View.INVISIBLE);
            jumlah2.setVisibility(View.INVISIBLE);
            harga2.setVisibility(View.INVISIBLE);
        }
    }

    public void tambah2(View view) {
        jumlahbarang2++;
        jumlah2.setText(jumlahbarang2+"");
        if(beli2==1){
            if(beli1==1){
                jumlahtotal=0;
                jumlahtotal=hargabarang1*jumlahbarang1+hargabarang2*jumlahbarang2;
                total.setText("Total : Rp. "+jumlahtotal);
            }
            else{
                jumlahtotal=0;
                jumlahtotal=hargabarang2*jumlahbarang2;
                total.setText("Total : Rp. "+jumlahtotal);
            }
        }
    }

    public void kurang2(View view) {
        if(jumlahbarang2>0){
            jumlahbarang2--;
            jumlah2.setText(jumlahbarang2+"");
            if(beli2==1){
                if(beli1==1){
                    jumlahtotal=0;
                    jumlahtotal=hargabarang1*jumlahbarang1+hargabarang2*jumlahbarang2;
                    total.setText("Total : Rp. "+jumlahtotal);
                }
                else{
                    jumlahtotal=0;
                    jumlahtotal=hargabarang2*jumlahbarang2;
                    total.setText("Total : Rp. "+jumlahtotal);
                }
            }
        }
    }

    public void hapus2(View view) {
        nama2.setText("kosong");
        pilih2.setVisibility(View.INVISIBLE);
        gambar2.setVisibility(View.INVISIBLE);
        nama2.setVisibility(View.INVISIBLE);
        tambah2.setVisibility(View.INVISIBLE);
        kurang2.setVisibility(View.INVISIBLE);
        hapus2.setVisibility(View.INVISIBLE);
        jumlah2.setVisibility(View.INVISIBLE);
        harga2.setVisibility(View.INVISIBLE);
        beli2=0;
        jumlahtotal=jumlahtotal-hargabarang2*jumlahbarang2;
        total.setText("Total : Rp. "+jumlahtotal);
        for (int i = 0; i < listWishlist.size(); i++) {
            if(listWishlist.get(i).namabarang.equals(nama1.getText().toString())&&listWishlist.get(i).yangbeli.equals(userwish)){
                listWishlist.remove(i);
            }
        }
        jumlahbarang2=0;
        hargabarang2=0;
    }

    public void pilih1(View view) {
        if(beli1==0){
            beli1=1;
        }
        else{
            beli1=0;
        }
        if(beli1==0){
            jumlahtotal=jumlahtotal-hargabarang1*jumlahbarang1;
            total.setText("Total : Rp. "+jumlahtotal);
        }
        else{
            jumlahtotal=jumlahtotal+hargabarang1*jumlahbarang1;
            total.setText("Total : Rp. "+jumlahtotal);
        }
    }

    public void pilih2(View view) {
        if(beli2==0){
            beli2=1;
        }
        else{
            beli2=0;
        }
        if(beli2==0){
            jumlahtotal=jumlahtotal-hargabarang2*jumlahbarang2;
            total.setText("Total : Rp. "+jumlahtotal);
        }
        else{
            jumlahtotal=jumlahtotal+hargabarang2*jumlahbarang2;
            total.setText("Total : Rp. "+jumlahtotal);
        }

    }
}