package com.example.testapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testapplication.Adapter.ProductAdapter;
import com.example.testapplication.Model.Product;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText editTextSearch;
    private Button buttonSearch;
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo danh sách sản phẩm mẫu
        productList = new ArrayList<>();
        productList.add(new Product("Áo Thun Đỏ", 200000, R.drawable.productfive));
        productList.add(new Product("Áo Thun Xanh", 250000, R.drawable.productfive));
        productList.add(new Product("Áo Thun Tím", 220000, R.drawable.productfive));
        productList.add(new Product("Quần Jean Xanh", 300000, R.drawable.ic_launcher_background));
        productList.add(new Product("Quần Âu Đen", 350000, R.drawable.ic_launcher_background));
        productList.add(new Product("Giày Thể Thao Trắng", 500000, R.drawable.ic_launcher_background));
        productList.add(new Product("Giày Da Đen", 600000, R.drawable.ic_launcher_background));
        productList.add(new Product("Áo Khoác Nữ", 700000, R.drawable.ic_launcher_background));

        productAdapter = new ProductAdapter(productList);
        recyclerViewProducts.setAdapter(productAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = editTextSearch.getText().toString().trim();
                if (!keyword.isEmpty()) {
                    searchProducts(keyword);
                } else {
                    Toast.makeText(SearchActivity.this, "Vui lòng nhập từ khóa.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void searchProducts(String keyword) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(product);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Không tìm thấy sản phẩm nào.", Toast.LENGTH_SHORT).show();
        }

        productAdapter = new ProductAdapter(filteredList);
        recyclerViewProducts.setAdapter(productAdapter);
    }
}
