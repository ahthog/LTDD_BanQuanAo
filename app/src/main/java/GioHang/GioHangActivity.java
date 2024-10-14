package GioHang;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.TextView;

import com.example.testapplication.R;

import DatHang.DatHangActivity;

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
        int goc1 = cartViewModel.getTienGoc1().getValue() != null ? cartViewModel.getTienGoc1().getValue() : 0;
        int goc2 = cartViewModel.getTienGoc2().getValue() != null ? cartViewModel.getTienGoc2().getValue() : 0;
        int goc3 = cartViewModel.getTienGoc3().getValue() != null ? cartViewModel.getTienGoc3().getValue() : 0;

        int giam1 = cartViewModel.getTienGiam1().getValue() != null ? cartViewModel.getTienGiam1().getValue() : 0;
        int giam2 = cartViewModel.getTienGiam2().getValue() != null ? cartViewModel.getTienGiam2().getValue() : 0;
        int giam3 = cartViewModel.getTienGiam3().getValue() != null ? cartViewModel.getTienGiam3().getValue() : 0;

        int tong1 = cartViewModel.getTongTien1().getValue() != null ? cartViewModel.getTongTien1().getValue() : 0;
        int tong2 = cartViewModel.getTongTien2().getValue() != null ? cartViewModel.getTongTien2().getValue() : 0;
        int tong3 = cartViewModel.getTongTien3().getValue() != null ? cartViewModel.getTongTien3().getValue() : 0;

        // Tính toán các giá trị cần thiết
        int tongCong = goc1 + goc2 + goc3; // Tổng cộng
        int giamGia = giam1 + giam2 + giam3; // Tính tổng giảm giá
        int thanhTien = tongCong - giamGia; // Thành tiền = Tổng cộng - Giảm giá

        // Cập nhật các TextView
        tongCongTextView.setText(String.valueOf(tongCong)); // Hiển thị tổng cộng
        giamGiaTextView.setText(String.valueOf(giamGia)); // Hiển thị giảm giá
        thanhTienTextView.setText(String.valueOf(thanhTien)); // Hiển thị thành tiền
    }
}
