package com.example.testapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Nhận các thuộc tính từ Intent
        Intent intent = getIntent();
        int productImage = intent.getIntExtra("productImage", 0);
        String productName = intent.getStringExtra("productName");
        String productColor = intent.getStringExtra("productColor");
        String productDescription = intent.getStringExtra("productDescription");
        String productPrice = intent.getStringExtra("productPrice");

        // Cập nhật giao diện với các thuộc tính nhận được
        ImageView imageView = findViewById(R.id.productImage);
        imageView.setImageResource(productImage);

        TextView nameTextView = findViewById(R.id.productName);
        nameTextView.setText(productName);

        TextView colorTextView = findViewById(R.id.productColor);
        colorTextView.setText("Màu:" + productColor);
        colorTextView.setTypeface(null, Typeface.BOLD);

        TextView descriptionTextView = findViewById(R.id.productDescription);
        descriptionTextView.setText(productDescription);

        TextView priceTextView = findViewById(R.id.productPrice);
        priceTextView.setText(productPrice);
    }

}
