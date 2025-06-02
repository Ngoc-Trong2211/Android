package com.example.lab14_2;

public class Item {
    private String id;
    private String name;
    private int img;

    public Item() {
    }

    public Item(String id, int img, String name) {
        this.id = id;
        this.img = img;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
