package com.example.testapplication;

// CategoryActivity.java

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.testapplication.Adapter.CategoryAdapter;
import com.example.testapplication.Model.Category;
import com.example.testapplication.Model.Product;
import com.example.testapplication.Model.ProductListActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCategories;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_category);  // Đảm bảo layout này tồn tại

        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this));

        // Tạo danh sách dữ liệu mẫu
        categoryList = new ArrayList<>();

        // Tạo danh sách sản phẩm mẫu cho mỗi danh mục
        List<Product> shirts = new ArrayList<>();
        shirts.add(new Product("Áo Thun Đỏ", 200000, R.drawable.ic_launcher_background));
        shirts.add(new Product("Áo Thun Xanh", 250000, R.drawable.ic_launcher_background));
        shirts.add(new Product("Áo Thun Đỏ", 200000, R.drawable.ic_launcher_background));
        shirts.add(new Product("Áo Thun Xanh", 250000, R.drawable.ic_launcher_background));
        shirts.add(new Product("Áo Thun Đỏ", 200000, R.drawable.ic_launcher_background));
        shirts.add(new Product("Áo Thun Xanh", 250000, R.drawable.ic_launcher_background));
        shirts.add(new Product("Áo Thun Đỏ", 200000, R.drawable.ic_launcher_background));
        shirts.add(new Product("Áo Thun Xanh", 250000, R.drawable.ic_launcher_background));
        shirts.add(new Product("Áo Thun Đỏ", 200000, R.drawable.ic_launcher_background));
        shirts.add(new Product("Áo Thun Xanh", 250000, R.drawable.ic_launcher_background));


        List<Product> pants = new ArrayList<>();
        pants.add(new Product("Quần Jean Xanh", 300000, R.drawable.ic_launcher_background));
        pants.add(new Product("Quần Âu Đen", 350000, R.drawable.ic_launcher_background));

        categoryList.add(new Category("Áo", R.drawable.ic_launcher_background, shirts));
        categoryList.add(new Category("Quần", R.drawable.ic_launcher_background, pants));

        categoryAdapter = new CategoryAdapter(categoryList);
        recyclerViewCategories.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Category selectedCategory = categoryList.get(position);
                Intent intent = new Intent(CategoryActivity.this, ProductListActivity.class);
                // Truyền danh sách sản phẩm vào Intent
                intent.putParcelableArrayListExtra("productList", new ArrayList<>(selectedCategory.getProductList()));
                startActivity(intent);
            }
        });
    }
}

