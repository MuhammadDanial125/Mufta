package com.example.mufta.ui.models;

public class User {

    private String uid;
    public String name;
    private String emailid;
    public String profileImage;
    private String token;
    private String password;
    private String phonenumber;
    private String city;

    public User() {

    }

    public User(String uid, String name, String emailid, String profileImage, String password, String phonenumber, String city) {
        this.uid = uid;
        this.name = name;
        this.emailid = emailid;
        this.profileImage = profileImage;
        this.password = password;
        this.phonenumber = phonenumber;
        this.city = city;
    }

    public User(String name, String emailid, String profileImage, String phonenumber, String city) {
        this.name = name;
        this.emailid = emailid;
        this.profileImage = profileImage;
        this.phonenumber = phonenumber;
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
