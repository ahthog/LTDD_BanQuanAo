package com.example.testapplication.DetailFragment;

import java.io.Serializable;

public class ProductDetail implements Serializable {
    private int imageResource;
    private String name;
    private String color;
    private String description;
    private String price;
    private float rating;

    public ProductDetail(int imageResource, String name, String color, String description, String price, float rating) {
        this.imageResource = imageResource;
        this.name = name;
        this.color = color;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }
}

