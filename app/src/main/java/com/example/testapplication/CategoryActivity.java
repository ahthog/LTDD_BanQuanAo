package com.example.testapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.Adapter.CategoryAdapter;
import com.example.testapplication.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCategories;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu); // Đảm bảo rằng layout này khớp với tên file layout bạn sử dụng.

        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this));

        // Tạo danh sách dữ liệu giả (mock data) cho danh mục sản phẩm
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Áo thun", R.drawable.fb)); // Thay 'fb' bằng hình ảnh bạn có
        categoryList.add(new Category("Quần jeans", R.drawable.fb));
        categoryList.add(new Category("Áo khoác", R.drawable.fb));
        categoryList.add(new Category("Giày thể thao", R.drawable.fb));
        categoryList.add(new Category("Phụ kiện thời trang", R.drawable.fb));

        // Thiết lập adapter và gắn vào RecyclerView
        categoryAdapter = new CategoryAdapter(categoryList);
        recyclerViewCategories.setAdapter(categoryAdapter);

        // Xử lý sự kiện khi người dùng nhấn vào một danh mục
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Xử lý sự kiện click vào danh mục tại đây
                // Ví dụ: Toast thông báo danh mục được chọn
                String categoryName = categoryList.get(position).getName();
                Toast.makeText(CategoryActivity.this, "Bạn đã chọn: " + categoryName, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
