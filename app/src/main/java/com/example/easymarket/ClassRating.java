package com.example.easymarket;

import java.io.Serializable;

public class ClassRating implements Serializable {
    String yangrating;
    String toko;
    int rating;

    public ClassRating(String yangrating, String toko, int rating) {
        this.yangrating = yangrating;
        this.toko = toko;
        this.rating = rating;
    }

    public String getYangrating() {
        return yangrating;
    }

    public void setYangrating(String yangrating) {
        this.yangrating = yangrating;
    }

    public String getToko() {
        return toko;
    }

    public void setToko(String toko) {
        this.toko = toko;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
