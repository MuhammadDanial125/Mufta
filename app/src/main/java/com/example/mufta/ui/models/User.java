package com.example.mufta.ui.models;

public class User {

    private String uid;
    public String name;
    private String emailid;
    public String profileImage;
    private String token;

    public User() {

    }

    public User(String uid, String name, String emailid, String profileImage) {
        this.uid = uid;
        this.name = name;
        this.emailid = emailid;
        this.profileImage = profileImage;
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
