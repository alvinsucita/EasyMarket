package com.example.easymarket;

import java.io.Serializable;

public class ClassEvent implements Serializable {
    String judul;
    String desc;

    public ClassEvent(){}
    public ClassEvent(String judul, String desc) {
        this.judul = judul;
        this.desc = desc;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
