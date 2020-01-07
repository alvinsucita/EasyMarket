package com.example.easymarket;

import java.io.Serializable;

public class ClassChat implements Serializable {
    String isi;
    String yangkirim;
    String yangdikirim;
    String waktu;

    public ClassChat(){}
    public ClassChat(String isi, String yangkirim, String yangdikirim, String waktu) {
        this.isi = isi;
        this.yangkirim = yangkirim;
        this.yangdikirim = yangdikirim;
        this.waktu = waktu;
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

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
