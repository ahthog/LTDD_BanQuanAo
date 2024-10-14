package com.example.testapplication.Fragment;

public class MenuItem {
    private String text;
    private int imageResId;

    public MenuItem(String text, int imageResId) {
        this.text = text;
        this.imageResId = imageResId;
    }

    public String getText() {
        return text;
    }

    public int getImageResId() {
        return imageResId;
    }
}
