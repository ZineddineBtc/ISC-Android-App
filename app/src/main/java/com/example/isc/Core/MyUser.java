package com.example.isc.Core;

public class MyUser {
    private int profileImage;
    private String fullName, position, email, password;
    private long studentCardNumber;

    public MyUser(int profileImage, String fullName, String position){
        this.profileImage = profileImage;
        this.fullName = fullName;
        this.position = position;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
