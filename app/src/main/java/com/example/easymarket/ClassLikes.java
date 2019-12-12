package com.example.easymarket;

public class ClassLikes {
    String idbarang;
    String emailuser;
    int aktif;

    public ClassLikes(){}

    public ClassLikes(String idbarang, String emailuser, int aktif) {
        this.idbarang = idbarang;
        this.emailuser = emailuser;
        this.aktif = aktif;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(String idbarang) {
        this.idbarang = idbarang;
    }

    public String getEmailuser() {
        return emailuser;
    }

    public void setEmailuser(String emailuser) {
        this.emailuser = emailuser;
    }

    public int getAktif() {
        return aktif;
    }

    public void setAktif(int aktif) {
        this.aktif = aktif;
    }
}
