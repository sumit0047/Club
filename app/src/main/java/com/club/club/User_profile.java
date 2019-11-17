package com.club.club;

public class User_profile{
    public String name;
    public String usn;

    public User_profile() {
    }

    public User_profile(String name, String usn) {
        this.name = name;
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }
}
