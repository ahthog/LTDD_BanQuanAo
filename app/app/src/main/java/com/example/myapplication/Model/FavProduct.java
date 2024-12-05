package com.example.myapplication.Model;

import java.io.Serializable;

public class FavProduct implements Serializable {
    private int id;         // MaYeuThich
    private int userId;     // MaNguoiDung
    private int productId;  // MaSanPham
    private Product product; // Thông tin sản phẩm yêu thích (nếu cần)

    // Constructor đầy đủ
    public FavProduct(int id, int userId, int productId, Product product) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.product = product;
    }

    // Constructor đơn giản
    public FavProduct(int id, int userId, int productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "FavProduct{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", product=" + product +
                '}';
    }
}
