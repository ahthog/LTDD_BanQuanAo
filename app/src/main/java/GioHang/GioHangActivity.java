package GioHang;

import android.content.Intent;  // Thêm import này
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;  // Thêm import này

import com.example.testapplication.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.GioHangAdapter;
import DatHang.DatHangActivity;

public class GioHangActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GioHangAdapter adapter;
    private List<GioHang> gioHangList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.rcv_giohang);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo dữ liệu mẫu cho giỏ hàng
        gioHangList = new ArrayList<>();
        gioHangList.add(new GioHang("Sản phẩm 1", "L", 1, 2700000, 474000, 2226000, R.drawable.mathang1));
        gioHangList.add(new GioHang("Sản phẩm 2", "M", 2, 3200000, 520000, 5360000, R.drawable.mathang2));
        gioHangList.add(new GioHang("Sản phẩm 3", "S", 3, 1500000, 150000, 400000, R.drawable.mathang3));

        // Khởi tạo Adapter và gán vào RecyclerView
        adapter = new GioHangAdapter(this, gioHangList);
        recyclerView.setAdapter(adapter);

        // Thêm sự kiện click cho nút đặt hàng
        findViewById(R.id.button_dathang).setOnClickListener(v -> {
            Intent intent = new Intent(GioHangActivity.this, DatHangActivity.class);
            startActivity(intent);
        });
    }
}
