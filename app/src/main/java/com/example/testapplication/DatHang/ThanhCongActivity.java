package com.example.testapplication.DatHang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testapplication.Fragment_menubar.HomeFragment;
import com.example.testapplication.MainActivity;  // Đảm bảo rằng MainActivity là nơi chứa fragment trang chủ
import com.example.testapplication.R;

public class ThanhCongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_cong);

        // Tìm và liên kết ImageView quay lại
        ImageView imageBack = findViewById(R.id.imageback);

        // Đặt sự kiện khi nhấn vào ảnh
        imageBack.setOnClickListener(v -> {
            // Quay lại trang chủ bằng cách khởi động lại MainActivity (hoặc Activity khác chứa HomeFragment)
            Intent intent = new Intent(ThanhCongActivity.this, HomeFragment.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();  // Đóng Activity hiện tại
        });

        // Xử lý hệ thống thanh trạng thái (nếu cần)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
