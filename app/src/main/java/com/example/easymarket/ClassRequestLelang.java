package com.example.easymarket;

import java.io.Serializable;

public class ClassRequestLelang implements Serializable {
    String idbarang;
    int masuklelang;

    public ClassRequestLelang(){}
    public ClassRequestLelang(String idbarang, int masuklelang) {
        this.idbarang = idbarang;
        this.masuklelang = masuklelang;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(String idbarang) {
        this.idbarang = idbarang;
    }

    public int getMasuklelang() {
        return masuklelang;
    }

    public void setMasuklelang(int masuklelang) {
        this.masuklelang = masuklelang;
    }
}
