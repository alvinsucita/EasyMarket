package com.example.easymarket;

import java.io.Serializable;

public class ClassWishlist implements Serializable {
    String idbarang;
    String emailpembeli;

    public ClassWishlist(){}

    public ClassWishlist(String idbarang, String emailpembeli) {
        this.idbarang = idbarang;
        this.emailpembeli = emailpembeli;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(String idbarang) {
        this.idbarang = idbarang;
    }

    public String getEmailpembeli() {
        return emailpembeli;
    }

    public void setEmailpembeli(String emailpembeli) {
        this.emailpembeli = emailpembeli;
    }
}