package com.example.myapplication.Model;


import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
  private int resourceId;
  private String name;
  private List<Product> productList;

    public Category(String name, int resourceId, List<Product> productList ) {
        this.name = name;
        this.productList = productList;
        this.resourceId = resourceId;
    }

    public Category(List<Product> productList) {
        this.productList = productList;
    }

    public Category(String name, int resourceId) {
        this.name = name;
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
