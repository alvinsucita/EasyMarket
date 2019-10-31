package com.example.easymarket;

import java.io.Serializable;

public class ClassWishlist implements Serializable {
    String namabarang;
    String yangbeli;

    public ClassWishlist(String namabarang, String yangbeli) {
        this.namabarang = namabarang;
        this.yangbeli = yangbeli;
    }

    @Override
    public String toString() {
        return "ClassWishlist{" +
                "namabarang='" + namabarang + '\'' +
                ", yangbeli='" + yangbeli + '\'' +
                '}';
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getYangbeli() {
        return yangbeli;
    }

    public void setYangbeli(String yangbeli) {
        this.yangbeli = yangbeli;
    }
}