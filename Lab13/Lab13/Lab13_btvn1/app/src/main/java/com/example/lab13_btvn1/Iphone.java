package com.example.lab13_btvn1;

public class Iphone {
    private int img;
    private String name;
    private int price;

    public Iphone(int img, String name, int price) {
        this.img = img;
        this.name = name;
        this.price = price;
    }

    public Iphone() {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
