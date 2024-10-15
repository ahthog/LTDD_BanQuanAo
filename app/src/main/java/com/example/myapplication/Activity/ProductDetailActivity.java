package com.example.myapplication.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView textProductName, textProductDescription, textProductPrice, textProductColor;
    private ImageView imageProduct;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Ánh xạ các view từ layout
        textProductName = findViewById(R.id.product_detail_name);
        textProductDescription = findViewById(R.id.product_detail_description);
        textProductPrice = findViewById(R.id.product_detail_price);
        textProductColor = findViewById(R.id.product_detail_color);
        imageProduct = findViewById(R.id.image_product);
        ratingBar = findViewById(R.id.rating_bar);

        // Nhận dữ liệu từ Intent
        String name = getIntent().getStringExtra("product_name");
        int imageResource = getIntent().getIntExtra("product_image", 0);
        String description = getIntent().getStringExtra("product_description");
        String price = getIntent().getStringExtra("product_price");
        String color = getIntent().getStringExtra("product_color");
        float rating = getIntent().getFloatExtra("product_rating", 0);

        // Hiển thị dữ liệu lên các view
        textProductName.setText(name);
        imageProduct.setImageResource(imageResource);
        textProductDescription.setText(description);
        textProductPrice.setText(price);
        textProductColor.setText(color);
        ratingBar.setRating(rating);
    }
}
