package com.example.easymarket;

public class ClassComment {
    String idbarang;
    String nama;
    String isi;

    public ClassComment(String idbarang, String nama, String isi) {
        this.idbarang = idbarang;
        this.nama = nama;
        this.isi = isi;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(String idbarang) {
        this.idbarang = idbarang;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
