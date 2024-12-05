package com.example.myapplication.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id; // Mã sản phẩm
    private String imageUrl;  // Đổi kiểu int thành String để chứa URL ảnh
    private String name;
    private String color;
    private String description;
    private double price;
    private float rating;

    // Constructor
    public Product(int id, String color, String description, String imageUrl, String name, double price, float rating) {
        this.id = id;
        this.color = color;
        this.description = description;
        this.imageUrl = imageUrl;  // Đổi từ imageResource thành imageUrl
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    // Constructor chỉ có tên và URL ảnh (cho trường hợp đơn giản)
    public Product(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;  // Đổi từ imageResource thành imageUrl
    }

    // Getter and Setter cho id và các thuộc tính khác
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    // Getter và Setter
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

    public String getImageUrl() {
        return imageUrl;  // Trả về URL ảnh
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;  // Thiết lập URL ảnh
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
