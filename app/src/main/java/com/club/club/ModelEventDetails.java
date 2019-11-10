package com.club.club;

public class ModelEventDetails {

    String title;
    String image;
    String club;
    String desc;
    String date;
    String venue;
    String contact;
    String time;
    String regfee;
    String regf;
    String clubid;

    public ModelEventDetails(String title, String image, String club, String desc, String date, String venue, String contact, String time, String regfee, String regf, String clubid) {
        this.title = title;
        this.image = image;
        this.club = club;
        this.desc = desc;
        this.date = date;
        this.venue = venue;
        this.contact = contact;
        this.time = time;
        this.regfee = regfee;
        this.regf = regf;
        this.clubid = clubid;
    }

    public ModelEventDetails() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRegfee() {
        return regfee;
    }

    public void setRegfee(String regfee) {
        this.regfee = regfee;
    }

    public String getRegf() {
        return regf;
    }

    public void setRegf(String regf) {
        this.regf = regf;
    }

    public String getClubid() {
        return clubid;
    }

    public void setClubid(String clubid) {
        this.clubid = clubid;
    }
}
