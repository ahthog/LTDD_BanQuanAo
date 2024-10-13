package com.example.testapplication.Model;

// ProductListActivity.java

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.testapplication.Adapter.ProductAdapter;
import com.example.testapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);

        int numberOfColumns = 2;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numberOfColumns);
        recyclerViewProducts.setLayoutManager(gridLayoutManager);

        // Lấy danh sách sản phẩm từ Intent
        productList = getIntent().getParcelableArrayListExtra("productList");

        // Kiểm tra nếu danh sách sản phẩm null, tạo danh sách rỗng
        if (productList == null) {
            productList = new ArrayList<>();
        }

        productAdapter = new ProductAdapter(productList);
        recyclerViewProducts.setAdapter(productAdapter);
    }
}
