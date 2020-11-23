package com.example.modelpaper;

public class UserData {
    private String username,dob,password,gender;

    public UserData(String username, String dob, String password, String gender) {
        this.username = username;
        this.dob = dob;
        this.password = password;
        this.gender = gender;
    }

    public UserData() {
    }

    public String getUsername() {
        return username;
    }

    public String getDob() {
        return dob;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }
}
