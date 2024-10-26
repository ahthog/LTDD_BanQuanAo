package DatHang;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.ThanhCongAdapter;

public class ThanhCongActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ThanhCongAdapter adapter;
    private List<ThanhCong> danhSachThanhCong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_cong); // Thay đổi với layout của bạn

        recyclerView = findViewById(R.id.rcv_thanhcong);
        // Sử dụng GridLayoutManager để có 2 cột
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Khởi tạo danh sách
        danhSachThanhCong = new ArrayList<>();
        // Thêm dữ liệu vào danh sách
        danhSachThanhCong.add(new ThanhCong(R.drawable.mathang9, "Sản phẩm 1"));
        danhSachThanhCong.add(new ThanhCong(R.drawable.mathang10, "Sản phẩm 2"));
        danhSachThanhCong.add(new ThanhCong(R.drawable.mathang8, "Sản phẩm 3"));
        danhSachThanhCong.add(new ThanhCong(R.drawable.mathang4, "Sản phẩm 4"));
        danhSachThanhCong.add(new ThanhCong(R.drawable.mathang5, "Sản phẩm 5"));
        danhSachThanhCong.add(new ThanhCong(R.drawable.mathang6, "Sản phẩm 6"));
        danhSachThanhCong.add(new ThanhCong(R.drawable.mathang7, "Sản phẩm 7"));
        danhSachThanhCong.add(new ThanhCong(R.drawable.mathang3, "Sản phẩm 8"));

        // Tạo adapter và gán cho RecyclerView
        adapter = new ThanhCongAdapter(danhSachThanhCong, this);
        recyclerView.setAdapter(adapter);
    }
}
