package com.example.easymarket;

import java.io.Serializable;

public class ClassRequestLelang implements Serializable {
    String idbarang;

    public ClassRequestLelang(String idbarang) {
        this.idbarang = idbarang;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(String idbarang) {
        this.idbarang = idbarang;
    }
}
