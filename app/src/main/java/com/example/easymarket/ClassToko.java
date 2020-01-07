package com.example.easymarket;

import java.io.Serializable;

public class ClassToko implements Serializable {
    String nama;
    String email;
    String password;
    String aktif;
    String FirebaseUID;
    String daerahasal;
    int rating;

    public ClassToko(){}

    public ClassToko(String nama, String email, String password, String aktif, String firebaseUID, String daerahasal, int rating) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.aktif = aktif;
        FirebaseUID = firebaseUID;
        this.daerahasal = daerahasal;
        this.rating = rating;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public String getFirebaseUID() {
        return FirebaseUID;
    }

    public void setFirebaseUID(String firebaseUID) {
        FirebaseUID = firebaseUID;
    }

    public String getDaerahasal() {
        return daerahasal;
    }

    public void setDaerahasal(String daerahasal) {
        this.daerahasal = daerahasal;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
