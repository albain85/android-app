package com.example.myapplication;

import org.xml.sax.DTDHandler;

public class User {
    public String user;
    public String pass;
    public String uId;
    public boolean admin;
    public User(){

    }

    public User(String u , String p ,String  id , boolean ad){
        user = u;
        pass = p;
        uId = id;
        admin = ad;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
