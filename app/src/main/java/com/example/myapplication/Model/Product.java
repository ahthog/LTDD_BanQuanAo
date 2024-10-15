package com.example.myapplication.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private int imageResource;
    private String name;
    private String color;  // Đổi 'string' thành 'String'
    private String description;
    private String price;
    private float rating;

    public Product(String color, String description, int imageResource, String name, String price, float rating) {
        this.color = color;
        this.description = description;
        this.imageResource = imageResource;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public Product(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
