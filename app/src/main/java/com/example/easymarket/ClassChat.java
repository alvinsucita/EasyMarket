package com.example.easymarket;

import java.io.Serializable;

public class ClassChat implements Serializable {
    String isi;
    String yangkirim;
    String yangdikirim;

    public ClassChat(String isi, String yangkirim, String yangdikirim) {
        this.isi = isi;
        this.yangkirim = yangkirim;
        this.yangdikirim = yangdikirim;
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
