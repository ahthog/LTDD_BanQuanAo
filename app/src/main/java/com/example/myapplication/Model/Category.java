package com.example.myapplication.Model;


import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
  private int resourceId;
  private String name;
  private String imageUrl;
  private List<Product> productList;
  private List<Category> categoryList;

    public Category(String name, int resourceId, List<Product> productList, String imageUrl ) {
        this.name = name;
        this.productList = productList;
        this.resourceId = resourceId;
        this.imageUrl = imageUrl;
    }

    // Constructor chỉ có tên và URL ảnh (cho trường hợp đơn giản)
    public Category(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;  // Đổi từ imageResource thành imageUrl
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
