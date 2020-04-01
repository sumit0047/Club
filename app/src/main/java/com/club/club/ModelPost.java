package com.club.club;

public class ModelPost {

    private String club;
    private String desc;
    private String time;
    private String date;
    private String imagef;
    private String image;

    public ModelPost(String club, String desc, String time, String date, String imagef, String image) {
        this.club = club;
        this.desc = desc;
        this.time = time;
        this.date = date;
        this.imagef = imagef;
        this.image = image;
    }

    public ModelPost()
    {

    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImagef() {
        return imagef;
    }

    public void setImagef(String imagef) {
        this.imagef = imagef;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
