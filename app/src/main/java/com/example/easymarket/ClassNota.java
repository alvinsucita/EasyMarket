package com.example.easymarket;

import java.io.Serializable;

public class ClassNota implements Serializable {
    String nama;
    String nohp;
    String alamat;
    String kota;
    String kodepos;
    String pembayaran;
    String pengiriman;
    String jenispengiriman;
    int jumlahbarang;
    int hargapengiriman;
    int subtotal;
    int total;

    public ClassNota(String nama, String nohp, String alamat, String kota, String kodepos, String pembayaran, String pengiriman, String jenispengiriman, int jumlahbarang, int hargapengiriman, int subtotal, int total) {
        this.nama = nama;
        this.nohp = nohp;
        this.alamat = alamat;
        this.kota = kota;
        this.kodepos = kodepos;
        this.pembayaran = pembayaran;
        this.pengiriman = pengiriman;
        this.jenispengiriman = jenispengiriman;
        this.jumlahbarang = jumlahbarang;
        this.hargapengiriman = hargapengiriman;
        this.subtotal = subtotal;
        this.total = total;
    }

    @Override
    public String toString() {
        return "ClassNota{" +
                "nama='" + nama + '\'' +
                ", nohp='" + nohp + '\'' +
                ", alamat='" + alamat + '\'' +
                ", kota='" + kota + '\'' +
                ", kodepos='" + kodepos + '\'' +
                ", pembayaran='" + pembayaran + '\'' +
                ", pengiriman='" + pengiriman + '\'' +
                ", jenispengiriman='" + jenispengiriman + '\'' +
                ", jumlahbarang=" + jumlahbarang +
                ", hargapengiriman=" + hargapengiriman +
                ", subtotal=" + subtotal +
                ", total=" + total +
                '}';
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getPengiriman() {
        return pengiriman;
    }

    public void setPengiriman(String pengiriman) {
        this.pengiriman = pengiriman;
    }

    public String getJenispengiriman() {
        return jenispengiriman;
    }

    public void setJenispengiriman(String jenispengiriman) {
        this.jenispengiriman = jenispengiriman;
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
