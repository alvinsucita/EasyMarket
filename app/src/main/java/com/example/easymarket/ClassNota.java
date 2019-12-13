package com.example.easymarket;

import java.io.Serializable;

public class ClassNota implements Serializable {
    String idnota;
    String namatoko;
    String idbarang;
    String namauser;
    String alamat;
    String pembayaran;
    String jenispengiriman;
    int hargabarang;
    int jumlahbarang;
    int hargapengiriman;
    int total;

    public ClassNota(){};
    public ClassNota(String idnota, String namatoko, String idbarang, String namauser, String alamat, String pembayaran, String jenispengiriman, int hargabarang, int jumlahbarang, int hargapengiriman, int total) {
        this.idnota = idnota;
        this.namatoko = namatoko;
        this.idbarang = idbarang;
        this.namauser = namauser;
        this.alamat = alamat;
        this.pembayaran = pembayaran;
        this.jenispengiriman = jenispengiriman;
        this.hargabarang = hargabarang;
        this.jumlahbarang = jumlahbarang;
        this.hargapengiriman = hargapengiriman;
        this.total = total;
    }

    public String getIdnota() {
        return idnota;
    }

    public void setIdnota(String idnota) {
        this.idnota = idnota;
    }

    public String getNamatoko() {
        return namatoko;
    }

    public void setNamatoko(String namatoko) {
        this.namatoko = namatoko;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(String idbarang) {
        this.idbarang = idbarang;
    }

    public String getNamauser() {
        return namauser;
    }

    public void setNamauser(String namauser) {
        this.namauser = namauser;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }

    public String getJenispengiriman() {
        return jenispengiriman;
    }

    public void setJenispengiriman(String jenispengiriman) {
        this.jenispengiriman = jenispengiriman;
    }

    public int getHargabarang() {
        return hargabarang;
    }

    public void setHargabarang(int hargabarang) {
        this.hargabarang = hargabarang;
    }

    public int getJumlahbarang() {
        return jumlahbarang;
    }

    public void setJumlahbarang(int jumlahbarang) {
        this.jumlahbarang = jumlahbarang;
    }

    public int getHargapengiriman() {
        return hargapengiriman;
    }

    public void setHargapengiriman(int hargapengiriman) {
        this.hargapengiriman = hargapengiriman;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
