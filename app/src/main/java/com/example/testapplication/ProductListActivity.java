package com.example.testapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager; // Import GridLayoutManager
import androidx.recyclerview.widget.RecyclerView;
import com.example.testapplication.Adapter.ProductAdapter;
import com.example.testapplication.Model.Product;
import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        // Sử dụng GridLayoutManager với 2 cột
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2));

        // Nhận danh sách sản phẩm từ Intent
        ArrayList<Product> productList = getIntent().getParcelableArrayListExtra("productList");

        // Khởi tạo Adapter
        productAdapter = new ProductAdapter(productList);
        recyclerViewProducts.setAdapter(productAdapter);
    }
}
