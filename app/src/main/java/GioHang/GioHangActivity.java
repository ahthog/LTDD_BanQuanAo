package GioHang;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.testapplication.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.GioHangAdapter;
import DatHang.DatHangActivity;

public class GioHangActivity extends AppCompatActivity implements GioHangAdapter.OnQuantityChangeListener {

    private RecyclerView recyclerView;
    private GioHangAdapter adapter;
    private List<GioHang> gioHangList;
    private TextView tongCongValue; // Tổng tiền ban đầu
    private TextView tongGiamValue; // Tổng tiền giảm
    private TextView thanhTienValue; // Thành tiền

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.rcv_giohang);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo TextView cho tổng tiền, tổng giảm giá, và thành tiền
        tongCongValue = findViewById(R.id.tongcong_value);
        tongGiamValue = findViewById(R.id.giamgia_value);
        thanhTienValue = findViewById(R.id.thanhtien_value); // Thêm TextView cho thành tiền

        // Tạo dữ liệu mẫu cho giỏ hàng
        gioHangList = new ArrayList<>();
        gioHangList.add(new GioHang("Sản phẩm 1", "L", 1, 2700000, 474000, 2226000, R.drawable.mathang1));
        gioHangList.add(new GioHang("Sản phẩm 2", "M", 2, 3200000, 520000, 5360000, R.drawable.mathang2));
        gioHangList.add(new GioHang("Sản phẩm 3", "S", 3, 1500000, 150000, 400000, R.drawable.mathang3));

        int totalQuantity = 0;
        double tongCong = 0; // Tổng tiền
        double tongGiam = 0; // Tổng giảm giá

        // Khởi tạo Adapter và gán vào RecyclerView
        adapter = new GioHangAdapter(this, gioHangList, this);
        recyclerView.setAdapter(adapter);

        // Tính tổng tiền, tổng giảm và thành tiền
        updateTongCong();
        updateTongGiam();
        updateThanhTien();

        // Thêm sự kiện click cho nút đặt hàng
        findViewById(R.id.button_dathang).setOnClickListener(v -> {
            Intent intent = new Intent(GioHangActivity.this, DatHangActivity.class);
            intent.putParcelableArrayListExtra("gioHangList", new ArrayList<>(gioHangList)); // Kiểm tra tên khóa

            // Thêm tổng tiền, tổng giảm và thành tiền vào Intent
            intent.putExtra("tongCong", Integer.parseInt(tongCongValue.getText().toString()));
            intent.putExtra("tongGiam", Integer.parseInt(tongGiamValue.getText().toString()));
            intent.putExtra("thanhTien", Integer.parseInt(thanhTienValue.getText().toString()));
            intent.putExtra("soLuongMatHang", totalQuantity);
            startActivity(intent);

        });

    }

    // Phương thức tính tổng tiền ban đầu
    public void updateTongCong() {
        int tongCong = 0;
        for (GioHang item : gioHangList) {
            tongCong += item.getGiaGoc() * item.getSoLuong(); // Cộng dồn tổng tiền trước giảm
        }
        tongCongValue.setText(String.valueOf(tongCong));  // Cập nhật vào TextView
    }

    // Phương thức tính tổng tiền giảm
    public void updateTongGiam() {
        int tongGiam = 0;
        for (GioHang item : gioHangList) {
            tongGiam += item.getGiaGiam() * item.getSoLuong(); // Cộng dồn tổng tiền giảm
        }
        tongGiamValue.setText(String.valueOf(tongGiam)); // Cập nhật vào TextView
    }

    // Phương thức tính thành tiền
    public void updateThanhTien() {
        int tongCong = 0;
        int tongGiam = 0;

        // Tính lại tổng tiền và tổng giảm để tính thành tiền
        for (GioHang item : gioHangList) {
            tongCong += item.getGiaGoc() * item.getSoLuong();
            tongGiam += item.getGiaGiam() * item.getSoLuong();
        }

        int thanhTien = tongCong - tongGiam; // Tính thành tiền
        thanhTienValue.setText(String.valueOf(thanhTien)); // Cập nhật vào TextView
    }

    @Override
    public void onQuantityChanged() {
        updateTongCong(); // Cập nhật tổng tiền mỗi khi số lượng thay đổi
        updateTongGiam(); // Cập nhật tổng giảm giá mỗi khi số lượng thay đổi
        updateThanhTien(); // Cập nhật thành tiền mỗi khi số lượng thay đổi
    }
}
