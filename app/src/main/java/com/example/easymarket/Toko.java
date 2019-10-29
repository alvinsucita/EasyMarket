package com.example.easymarket;

import java.io.Serializable;

public class Toko implements Serializable {
    String nama;
    String daerahasal;
    String email;
    String password;
    String aktif;

    public Toko(String nama, String daerahasal, String email, String password, String aktif) {
        this.nama = nama;
        this.daerahasal = daerahasal;
        this.email = email;
        this.password = password;
        this.aktif = aktif;
    }

    @Override
    public String toString() {
        return "Toko{" +
                "nama='" + nama + '\'' +
                ", daerahasal='" + daerahasal + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", aktif='" + aktif + '\'' +
                '}';
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDaerahasal() {
        return daerahasal;
    }

    public void setDaerahasal(String daerahasal) {
        this.daerahasal = daerahasal;
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
