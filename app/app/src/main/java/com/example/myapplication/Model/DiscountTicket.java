package com.example.myapplication.Model;

public class DiscountTicket {
    private String description;
    private int discountValue;

    public DiscountTicket(String description, int discountValue) {
        this.description = description;
        this.discountValue = discountValue;
    }

    public String getDescription() {
        return description;
    }

    public int getDiscountValue() {
        return discountValue;
    }
}


