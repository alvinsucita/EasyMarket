package com.example.easymarket;

import java.io.Serializable;

public class ClassNota implements Serializable {
    String idnota;
    String tanggal;
    String namatoko;
    String namabarang;
    String namauser;
    String nohp;
    String alamat;
    String kota;
    String kodepos;
    String pembayaran;
    String jenispengiriman;
    int hargabarang;
    int jumlahbarang;
    int hargapengiriman;
    int subtotal;
    int total;

    public ClassNota(String idnota, String tanggal, String namatoko, String namabarang, String namauser, String nohp, String alamat, String kota, String kodepos, String pembayaran, String jenispengiriman, int hargabarang, int jumlahbarang, int hargapengiriman, int subtotal, int total) {
        this.idnota = idnota;
        this.tanggal = tanggal;
        this.namatoko = namatoko;
        this.namabarang = namabarang;
        this.namauser = namauser;
        this.nohp = nohp;
        this.alamat = alamat;
        this.kota = kota;
        this.kodepos = kodepos;
        this.pembayaran = pembayaran;
        this.jenispengiriman = jenispengiriman;
        this.hargabarang = hargabarang;
        this.jumlahbarang = jumlahbarang;
        this.hargapengiriman = hargapengiriman;
        this.subtotal = subtotal;
        this.total = total;
    }

    public String getIdnota() {
        return idnota;
    }

    public void setIdnota(String idnota) {
        this.idnota = idnota;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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

    public String getNamauser() {
        return namauser;
    }

    public void setNamauser(String namauser) {
        this.namauser = namauser;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKodepos() {
        return kodepos;
    }

    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
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

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
