package com.example.easymarket;

import java.io.Serializable;

public class ClassUser implements Serializable {
    String email;
    String nama;
    String password;
    String FirebaseUID;
    String gender;
    String daerahasal;
    String aktif;
    int hari;
    int bulan;
    int tahun;

    public ClassUser(){}

    public ClassUser(String email, String nama, String password, String firebaseUID, String gender, String daerahasal, String aktif, int hari, int bulan, int tahun) {
        this.email = email;
        this.nama = nama;
        this.password = password;
        FirebaseUID = firebaseUID;
        this.gender = gender;
        this.daerahasal = daerahasal;
        this.aktif = aktif;
        this.hari = hari;
        this.bulan = bulan;
        this.tahun = tahun;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirebaseUID() {
        return FirebaseUID;
    }

    public void setFirebaseUID(String firebaseUID) {
        FirebaseUID = firebaseUID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDaerahasal() {
        return daerahasal;
    }

    public void setDaerahasal(String daerahasal) {
        this.daerahasal = daerahasal;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public int getHari() {
        return hari;
    }

    public void setHari(int hari) {
        this.hari = hari;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }
}