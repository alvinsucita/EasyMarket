package com.example.easymarket;

import java.io.Serializable;

public class ClassChat implements Serializable {
    String nama;
    String isi;
    String yangkirim;
    String yangdikirim;

    public ClassChat(String nama, String isi, String yangkirim, String yangdikirim) {
        this.nama = nama;
        this.isi = isi;
        this.yangkirim = yangkirim;
        this.yangdikirim = yangdikirim;
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

    public String getYangkirim() {
        return yangkirim;
    }

    public void setYangkirim(String yangkirim) {
        this.yangkirim = yangkirim;
    }

    public String getYangdikirim() {
        return yangdikirim;
    }

    public void setYangdikirim(String yangdikirim) {
        this.yangdikirim = yangdikirim;
    }
}
