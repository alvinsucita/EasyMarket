package com.example.easymarket;

import java.io.Serializable;

public class ClassLelang implements Serializable {
    String idbarang;
    int harganormal;
    int hargatertinggi;
    String namabidder;

    public ClassLelang(){}
    public ClassLelang(String idbarang, int harganormal, int hargatertinggi, String namabidder) {
        this.idbarang = idbarang;
        this.harganormal = harganormal;
        this.hargatertinggi = hargatertinggi;
        this.namabidder = namabidder;
    }

    public int getHarganormal() {
        return harganormal;
    }

    public void setHarganormal(int harganormal) {
        this.harganormal = harganormal;
    }

    public int getHargatertinggi() {
        return hargatertinggi;
    }

    public void setHargatertinggi(int hargatertinggi) {
        this.hargatertinggi = hargatertinggi;
    }

    public String getNamabidder() {
        return namabidder;
    }

    public void setNamabidder(String namabidder) {
        this.namabidder = namabidder;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(String idbarang) {
        this.idbarang = idbarang;
    }

}
