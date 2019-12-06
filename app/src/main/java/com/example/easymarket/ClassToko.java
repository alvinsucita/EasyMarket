package com.example.easymarket;

import java.io.Serializable;

public class ClassToko implements Serializable {
    String nama;
    String daerahasal;
    String email;
    String password;

    public ClassToko(String nama, String daerahasal, String email, String password) {
        this.nama = nama;
        this.daerahasal = daerahasal;
        this.email = email;
        this.password = password;
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
}
