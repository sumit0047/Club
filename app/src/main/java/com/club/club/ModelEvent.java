package com.club.club;

public class ModelEvent {

    private String image;
    private String title;

    public ModelEvent(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public ModelEvent() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
