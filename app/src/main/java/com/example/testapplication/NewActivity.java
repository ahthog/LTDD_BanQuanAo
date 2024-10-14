package com.example.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new); // Layout mới bạn muốn chuyển đến

        // Nhận dữ liệu truyền từ Adapter (nếu có)
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");

        // Ví dụ: Cập nhật TextView với tên sản phẩm
        TextView productNameTextView = findViewById(R.id.tv_product_name_new);
        productNameTextView.setText(productName);
    }
}
