package com.example.easymarket;

import java.io.Serializable;

public class ClassRequestLelang implements Serializable {
    String idbarang;
    boolean masuklelang;

    public boolean isMasuklelang() {
        return masuklelang;
    }

    public void setMasuklelang(boolean masuklelang) {
        this.masuklelang = masuklelang;
    }

    public ClassRequestLelang(String idbarang) {
        this.idbarang = idbarang;
        this.masuklelang = true;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(String idbarang) {
        this.idbarang = idbarang;
    }
}
