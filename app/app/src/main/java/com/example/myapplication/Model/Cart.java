package com.example.myapplication.Model;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;

import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private double overallDiscount = 0.0;  // Giảm giá chung cho toàn bộ giỏ hàng

    public void addItem(CartItem item) {
        cartItems.add(item);
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    // Đặt giảm giá cho toàn bộ giỏ hàng (theo %)
    public void setOverallDiscount(double discount) {
        this.overallDiscount = discount;
    }

    // Tính tổng số tiền trước giảm giá
    public double getTotalAmount() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public int getTotalItems() {
        int totalItems = 0;
        for (CartItem item : cartItems) {
            totalItems += item.getQuantity(); // Giả sử mỗi CartItem có thuộc tính quantity
        }
        return totalItems;
    }


    // Tính tổng tiền sau khi trừ giảm giá
    public double getTotalAfterDiscount() {
        double totalAmount = getTotalAmount();
        double discountAmount = totalAmount * (overallDiscount / 100);
        return totalAmount - discountAmount;
    }

    // Tính tổng số tiền giảm giá
    public double getDiscountAmount() {
        return getTotalAmount() * (overallDiscount / 100);
    }

    // Thêm phương thức clear để xóa tất cả các mục trong giỏ hàng
    public void clear() {
        cartItems.clear();
    }
}
