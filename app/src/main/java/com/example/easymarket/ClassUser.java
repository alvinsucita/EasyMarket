package com.example.easymarket;

import java.io.Serializable;

public class ClassUser implements Serializable {
    String email;
    String nama;
    String password;
    String gender;
    String daerahasal;
    String umur;
    String aktif;

    public ClassUser(){}

    public ClassUser(String email, String nama, String password, String gender, String daerahasal, String umur, String aktif) {
        this.email = email;
        this.nama = nama;
        this.password = password;
        this.gender = gender;
        this.daerahasal = daerahasal;
        this.umur = umur;
        this.aktif = aktif;
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

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }
}