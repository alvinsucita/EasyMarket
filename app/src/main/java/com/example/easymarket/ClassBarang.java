package com.example.easymarket;

import java.io.Serializable;

public class ClassBarang implements Serializable {
    String idbarang;
    String namatoko;
    String namabarang;
    String deskripsi;
    String kategori;
    int harga;
    int likes;
    int dilihat;
    int dibeli;
    int stok;
    public ClassBarang(){}

    public ClassBarang(String idbarang, String namatoko, String namabarang, String deskripsi, String kategori, int harga, int likes, int dilihat, int dibeli, int stok) {
        this.idbarang = idbarang;
        this.namatoko = namatoko;
        this.namabarang = namabarang;
        this.deskripsi = deskripsi;
        this.kategori = kategori;
        this.harga = harga;
        this.likes = likes;
        this.dilihat = dilihat;
        this.dibeli = dibeli;
        this.stok = stok;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(String idbarang) {
        this.idbarang = idbarang;
    }

    public String getNamatoko() {
        return namatoko;
    }

    public void setNamatoko(String namatoko) {
        this.namatoko = namatoko;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDilihat() {
        return dilihat;
    }

    public void setDilihat(int dilihat) {
        this.dilihat = dilihat;
    }

    public int getDibeli() {
        return dibeli;
    }

    public void setDibeli(int dibeli) {
        this.dibeli = dibeli;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}