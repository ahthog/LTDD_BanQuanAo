package com.example.testapplication.Fragment_menubar;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product implements Parcelable{
    private int imageResource;
    private String name;
    private String color;  // Đổi 'string' thành 'String'
    private String description;
    private String price;
    private float rating;

    public Product(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    // Constructor với tất cả các tham số
    public Product(int imageResource, String name, String color, String description, String price, float rating) {
        this.imageResource = imageResource;
        this.name = name;
        this.color = color;  // Thêm biến 'color' vào constructor
        this.description = description;
        this.price = price;
        this.rating = rating;
    }
    public Product(String name, String price, int imageResource) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
    }
    // Thêm constructor không có rating
    public Product(int imageResource, String name, String color, String price) {
        this.imageResource = imageResource;
        this.name = name;
        this.color = color;
        this.price = price;
        this.rating = 0.0f; // Mặc định rating là 0.0
    }

    // Constructor chỉ với hình ảnh và tên sản phẩm
    public Product(int imageResource, String name) {
        this.imageResource = imageResource;
        this.name = name;
        this.description = "";  // Mặc định mô tả là chuỗi rỗng
        this.price = "";        // Mặc định giá là chuỗi rỗng
        this.rating = 0.0f;     // Mặc định đánh giá là 0.0
    }

    // Constructor chỉ với hình ảnh
    public Product(int imageResource) {
        this.imageResource = imageResource;
        this.name = "";         // Mặc định tên là chuỗi rỗng
        this.description = "";  // Mặc định mô tả là chuỗi rỗng
        this.price = "";        // Mặc định giá là chuỗi rỗng
        this.rating = 0.0f;     // Mặc định đánh giá là 0.0
    }

    // Getter và Setter
    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    protected Product(Parcel in) {
        name = in.readString();
        price = in.readString();
        imageResource = in.readInt();
    }
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }
}
