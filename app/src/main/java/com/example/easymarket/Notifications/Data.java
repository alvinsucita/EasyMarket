package com.example.easymarket.Notifications;

public class Data {
    private String user;
    private int icon;
    private String body;
    private String title;
    private String sented;
    public Data(){}
    public Data(String user,int icon,String body, String title,String sented){
        this.user=user;
        this.icon=icon;
        this.body=body;
        this.title=title;
        this.sented=sented;
    }

    public String getUser() {
        return user;
    }

    public int getIcon() {
        return icon;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public String getSented() {
        return sented;
    }
}
