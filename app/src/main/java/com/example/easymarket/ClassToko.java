package com.example.easymarket;

import java.io.Serializable;

public class ClassToko implements Serializable {
    String nama;
    String email;
    String password;
    String aktif;

    public ClassToko(String nama, String email, String password, String aktif) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.aktif = aktif;
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
}
