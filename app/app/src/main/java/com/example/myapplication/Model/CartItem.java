package com.example.myapplication.Model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int idCartItem;
    private String productName;
    private String size;
    private int quantity;
    private double price;
    private double discount;
    private String imageurl;
    private boolean isSelected;
    public CartItem(String productName, String size, int quantity, double price, double discount, String imageurl) {
        this.productName = productName;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.imageurl = imageurl;

    }
    // Getter and Setter cho id và các thuộc tính khác
    public int getId() {
        return idCartItem;
    }

    public void setId(int id) {
        this.idCartItem = id;
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public String getProductName() {
        return productName;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotalPrice() {
        return price * quantity * (1 - discount / 100);  // Tổng tiền sau giảm giá
    }

    public String getImageurl() {
        return imageurl;
    }
    // Phương thức tăng số lượng
    public void increaseQuantity() {
        this.quantity++;
    }

    // Phương thức giảm số lượng
    public void decreaseQuantity() {
        if (this.quantity > 1) {
            this.quantity--;
        }
    }
}
