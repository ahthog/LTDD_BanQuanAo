package com.example.testapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Category implements Parcelable {
    private String name;
    private int imageResource; // Hình ảnh của danh mục
    private List<Product> productList; // Danh sách sản phẩm trong danh mục

    public Category(String name, int imageResource, List<Product> productList) {
        this.name = name;
        this.imageResource = imageResource;
        this.productList = productList;
    }

    protected Category(Parcel in) {
        name = in.readString();
        imageResource = in.readInt();
        productList = in.createTypedArrayList(Product.CREATOR);
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(imageResource);
        dest.writeTypedList(productList);
    }
}
