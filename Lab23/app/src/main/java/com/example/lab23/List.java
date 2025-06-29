package com.example.lab23;

import android.graphics.Bitmap;

public class List {
    private Bitmap img;
    private String title;
    private String info;
    private String link;

    // Constructor
    public List(Bitmap img, String title, String info, String link) {
        this.img = img;
        this.title = title;
        this.info = info;
        this.link = link;
    }

    // Getter và Setter cho img
    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    // Getter và Setter cho title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter và Setter cho info
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    // Getter và Setter cho link
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
