package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.API.ApiServiceSize;
import com.example.myapplication.Manager.CartManager;
import com.example.myapplication.Model.CartItem;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView textProductName, textProductDescription, textProductPrice, textProductColor;
    private ImageView imageProduct;
    private RatingBar ratingBar;
    private CartManager cartManager; // Khai báo biến CartManager
    private String selectedSize = "M"; // Kích cỡ mặc định
    private Button sizeM, sizeL, sizeXL;
    private TextView sizeSelected;
    private LinearLayout sizeContainer; // Đặt một container (LinearLayout) để chứa các button size
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        sizeContainer = findViewById(R.id.sizeContainer); // Ánh xạ container
// Khởi tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.21.155:8081/appadroidbanquanao/") // URL gốc của API
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServiceSize apiService = retrofit.create(ApiServiceSize.class);

        // Ánh xạ các view từ layout
        textProductName = findViewById(R.id.product_detail_name);
        textProductDescription = findViewById(R.id.product_detail_description);
        textProductPrice = findViewById(R.id.product_detail_price);
        textProductColor = findViewById(R.id.product_detail_color);
        imageProduct = findViewById(R.id.image_product);
        ratingBar = findViewById(R.id.rating_bar);

        // Nhận dữ liệu từ Intent
        String name = getIntent().getStringExtra("product_name");


        String description = getIntent().getStringExtra("product_description");
        double price = getIntent().getDoubleExtra("product_price", 0.0);
        String color = getIntent().getStringExtra("product_color");
        float rating = getIntent().getFloatExtra("product_rating", 0);

        // Hiển thị dữ liệu lên các view
        textProductName.setText(name);
        textProductDescription.setText(description);

        // Định dạng giá thành chuỗi với tiền tệ
        String formattedPrice = String.format("%,.0f đ", price);
        textProductPrice.setText(formattedPrice);

        textProductColor.setText(color);
        ratingBar.setRating(rating);

        String baseUrl = "http://192.168.21.155:8081/appadroidbanquanao/images/"; // Địa chỉ cơ bản của server
        String imageUrl = baseUrl + getIntent().getStringExtra("product_image");  // Nối URL hoàn chỉnh

        Picasso.get()
                .load(imageUrl)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(imageProduct, new Callback() {
                    @Override
                    public void onSuccess() {
                        // Hình ảnh đã tải thành công
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("ImageLoadError", "Error loading image: " + e.getMessage());
                    }
                });



        // Khởi tạo CartManager
        cartManager = new CartManager(this);

        // Nút thêm vào giỏ hàng
        ImageButton addToCartBtn = findViewById(R.id.addToCartBtn);
        addToCartBtn.setOnClickListener(view -> {
            int quantity = 1;

            // Tạo CartItem từ các thuộc tính sản phẩm
            CartItem cartItem = new CartItem(
                    name,            // Sử dụng tên sản phẩm nhận từ Intent
                    selectedSize,    // Kích thước được chọn
                    quantity,
                    price,
                    0.0,            // Giả sử không có giảm giá
                    imageUrl        // Hình ảnh sản phẩm, dùng URL thay vì resource
            );

            // Thêm CartItem vào giỏ hàng
            cartManager.addToCart(cartItem);

            // Khởi tạo Intent để mở CartActivity
            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // Ánh xạ các button kích cỡ
        sizeM = findViewById(R.id.sizeM);
        sizeL = findViewById(R.id.sizeL);
        sizeXL = findViewById(R.id.sizeXL);
        sizeSelected = findViewById(R.id.sizeSelected);

        // Cài đặt sự kiện click cho button kích cỡ M
        sizeM.setOnClickListener(v -> {
            // Chỉ thay đổi màu button M
            sizeM.setBackgroundResource(R.drawable.button_selected);

            // Đặt lại màu cho các button khác về màu mặc định
            sizeL.setBackgroundResource(0);
            sizeXL.setBackgroundResource(0);

            // Cập nhật thông tin kích cỡ đã chọn
            sizeSelected.setText("Kích cỡ M");
            selectedSize = "M";
        });

        // Cài đặt sự kiện click cho button kích cỡ L
        sizeL.setOnClickListener(v -> {
            // Chỉ thay đổi màu button L
            sizeL.setBackgroundResource(R.drawable.button_selected);

            // Đặt lại màu cho các button còn lại về màu mặc định
            sizeM.setBackgroundResource(0);
            sizeXL.setBackgroundResource(0);

            // Cập nhật thông tin kích cỡ đã chọn
            sizeSelected.setText("Kích cỡ L");
            selectedSize = "L";
        });

        // Cài đặt sự kiện click cho button kích cỡ XL
        sizeXL.setOnClickListener(v -> {
            // Chỉ thay đổi màu button XL
            sizeXL.setBackgroundResource(R.drawable.button_selected);

            // Đặt lại màu cho các button còn lại về màu mặc định
            sizeM.setBackgroundResource(0);
            sizeL.setBackgroundResource(0);

            // Cập nhật thông tin kích cỡ đã chọn
            sizeSelected.setText("Kích cỡ XL");
            selectedSize = "XL";
        });
    }
}

