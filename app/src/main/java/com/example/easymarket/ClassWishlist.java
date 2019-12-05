package com.example.easymarket;

import java.io.Serializable;

public class ClassWishlist implements Serializable {
    String namabarang;
    String hargabarang;
    String yangbeli;

    public ClassWishlist(String namabarang, String hargabarang, String yangbeli) {
        this.namabarang = namabarang;
        this.hargabarang = hargabarang;
        this.yangbeli = yangbeli;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getHargabarang() {
        return hargabarang;
    }

    public void setHargabarang(String hargabarang) {
        this.hargabarang = hargabarang;
    }

    public String getYangbeli() {
        return yangbeli;
    }

    public void setYangbeli(String yangbeli) {
        this.yangbeli = yangbeli;
    }
}