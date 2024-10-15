package com.example.testapplication.GioHang;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.testapplication.DatHang.DatHangActivity;
import com.example.testapplication.R;

public class GioHangActivity extends AppCompatActivity {

    private CartViewModel cartViewModel; // Khai báo ViewModel
    private TextView tongCongTextView, giamGiaTextView, thanhTienTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gio_hang);

        // Khởi tạo ViewModel
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        // Ánh xạ các TextView
        tongCongTextView = findViewById(R.id.tongcong_value);
        giamGiaTextView = findViewById(R.id.giamgia_value);
        thanhTienTextView = findViewById(R.id.thanhtien_value);

        cartViewModel.setTienGoc1(8100000);
        cartViewModel.setTienGiam1(1422000);
        cartViewModel.setTongTien1(6678000);

        // Thiết lập Observer cho các LiveData
        cartViewModel.getTienGoc1().observe(this, value -> updateValues());
        cartViewModel.getTienGoc2().observe(this, value -> updateValues());
        cartViewModel.getTienGoc3().observe(this, value -> updateValues());
        cartViewModel.getTienGiam1().observe(this, value -> updateValues());
        cartViewModel.getTienGiam2().observe(this, value -> updateValues());
        cartViewModel.getTienGiam3().observe(this, value -> updateValues());
        cartViewModel.getTongTien1().observe(this, value -> updateValues());
        cartViewModel.getTongTien2().observe(this, value -> updateValues());
        cartViewModel.getTongTien3().observe(this, value -> updateValues());

        // Thiết lập listener cho button_dathang
        findViewById(R.id.button_dathang).setOnClickListener(v -> {
            Intent intent = new Intent(GioHangActivity.this, DatHangActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Phương thức cập nhật các giá trị trong TextView
    private void updateValues() {
        // Lấy giá trị mới nhất từ ViewModel
        int goc1 = cartViewModel.getTienGoc1().getValue() != null ? cartViewModel.getTienGoc1().getValue() : 0;
        int goc2 = cartViewModel.getTienGoc2().getValue() != null ? cartViewModel.getTienGoc2().getValue() : 0;
        int goc3 = cartViewModel.getTienGoc3().getValue() != null ? cartViewModel.getTienGoc3().getValue() : 0;

        int giam1 = cartViewModel.getTienGiam1().getValue() != null ? cartViewModel.getTienGiam1().getValue() : 0;
        int giam2 = cartViewModel.getTienGiam2().getValue() != null ? cartViewModel.getTienGiam2().getValue() : 0;
        int giam3 = cartViewModel.getTienGiam3().getValue() != null ? cartViewModel.getTienGiam3().getValue() : 0;

        // Tính tổng cộng
        int tongCong = goc1 + goc2 + goc3;
        int giamGia = giam1 + giam2 + giam3;
        int thanhTien = tongCong - giamGia;

        // Cập nhật UI
        tongCongTextView.setText(String.valueOf(tongCong));
        giamGiaTextView.setText(String.valueOf(giamGia));
        thanhTienTextView.setText(String.valueOf(thanhTien));
    }

}
