package com.example.testapplication.Model;

import java.util.List;

public class Category {
    private String name;
    private int imageResource;
    private List<Product> productList;

    public Category(String name, int imageResource, List<Product> productList) {
        this.name = name;
        this.imageResource = imageResource;
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
