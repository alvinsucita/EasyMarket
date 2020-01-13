package com.example.easymarket;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "barang")
public class _BarangBF {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="idbarang")
    private int id;

    @ColumnInfo(name="namabarang")
    private String namaBrg;

    @ColumnInfo(name="harga_retail")
    private String harga_retail;

    @ColumnInfo(name="durasi")
    private String durasi;

    @ColumnInfo(name="init_date")
    private String init_date;

    @ColumnInfo(name="owner")
    private String owner;

    @ColumnInfo(name="highest_bid")
    private int highest_bid;

    public _BarangBF(String namaBrg, String harga_retail, String init_date, String owner) {
        this.namaBrg = namaBrg;
        this.harga_retail = harga_retail;
        this.init_date = init_date;
        this.owner = owner;
        this.highest_bid = 0;
    }

    public String getNamaBrg() {
        return namaBrg;
    }

    public void setNamaBrg(String namaBrg) {
        this.namaBrg = namaBrg;
    }

    public String getHarga_retail() {
        return harga_retail;
    }

    public void setHarga_retail(String harga_retail) {
        this.harga_retail = harga_retail;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getInit_date() {
        return init_date;
    }

    public void setInit_date(String init_date) {
        this.init_date = init_date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getHighest_bid() {
        return highest_bid;
    }

    public void setHighest_bid(int highest_bid) {
        this.highest_bid = highest_bid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
