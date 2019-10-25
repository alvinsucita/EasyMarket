package com.example.easymarket;

import android.os.Parcel;
import android.os.Parcelable;

public class Barang implements Parcelable {

    String idBarang,idPembeli,tipeBarang,beratBarang,
            deskBarang,satuanBarang,rateBarang,emailPembeli,alamatPembeli;

    public Barang(String idBarang, String idPembeli, String tipeBarang,
                  String beratBarang, String deskBarang, String satuanBarang,
                  String rateBarang, String emailPembeli, String alamatPembeli) {
        this.idBarang = idBarang;
        this.idPembeli = idPembeli;
        this.tipeBarang = tipeBarang;
        this.beratBarang = beratBarang;
        this.deskBarang = deskBarang;
        this.satuanBarang = satuanBarang;
        this.rateBarang = rateBarang;
        this.emailPembeli = emailPembeli;
        this.alamatPembeli = alamatPembeli;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(String idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getTipeBarang() {
        return tipeBarang;
    }

    public void setTipeBarang(String tipeBarang) {
        this.tipeBarang = tipeBarang;
    }

    public String getBeratBarang() {
        return beratBarang;
    }

    public void setBeratBarang(String beratBarang) {
        this.beratBarang = beratBarang;
    }

    public String getDeskBarang() {
        return deskBarang;
    }

    public void setDeskBarang(String deskBarang) {
        this.deskBarang = deskBarang;
    }

    public String getSatuanBarang() {
        return satuanBarang;
    }

    public void setSatuanBarang(String satuanBarang) {
        this.satuanBarang = satuanBarang;
    }

    public String getRateBarang() {
        return rateBarang;
    }

    public void setRateBarang(String rateBarang) {
        this.rateBarang = rateBarang;
    }

    public String getEmailPembeli() {
        return emailPembeli;
    }

    public void setEmailPembeli(String emailPembeli) {
        this.emailPembeli = emailPembeli;
    }

    public String getAlamatPembeli() {
        return alamatPembeli;
    }

    public void setAlamatPembeli(String alamatPembeli) {
        this.alamatPembeli = alamatPembeli;
    }

    protected Barang(Parcel in) {
    }

    public static final Creator<Barang> CREATOR = new Creator<Barang>() {
        @Override
        public Barang createFromParcel(Parcel in) {
            return new Barang(in);
        }

        @Override
        public Barang[] newArray(int size) {
            return new Barang[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
