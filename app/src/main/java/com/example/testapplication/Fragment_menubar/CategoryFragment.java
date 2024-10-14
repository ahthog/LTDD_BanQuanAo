package com.example.testapplication.Fragment_menubar;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testapplication.Adapter.CategoryAdapter;
import com.example.testapplication.Model.Category;
import com.example.testapplication.Model.Product;
import com.example.testapplication.ProductListActivity;
import com.example.testapplication.R;
import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    private RecyclerView recyclerViewCategories;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerViewCategories = view.findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext()));

        categoryList = new ArrayList<>();

        // Tạo danh sách sản phẩm mẫu cho mỗi danh mục
        List<Product> shirts = new ArrayList<>();
        shirts.add(new Product("Áo Thun Đỏ", 200000, R.drawable.productone));
        shirts.add(new Product("Áo Thun Xanh", 250000, R.drawable.producttwo));
        shirts.add(new Product("Áo Thun Tím", 200000, R.drawable.producthree));
        shirts.add(new Product("Áo Thun Hồng", 250000, R.drawable.productfour));
        shirts.add(new Product("Áo Thun Đỏ", 200000, R.drawable.productfive));
        shirts.add(new Product("Áo Thun HUU", 250000, R.drawable.productsix));
        shirts.add(new Product("Áo Thun Đỏ", 200000, R.drawable.productseven));
        shirts.add(new Product("Áo Thun Xanh", 250000, R.drawable.productone));

        List<Product> pants = new ArrayList<>();
        pants.add(new Product("Quần Jean Xanh", 300000, R.drawable.productfive));
        pants.add(new Product("Quần Âu Đen", 350000, R.drawable.productseven));

        List<Product> hat = new ArrayList<>();
        hat.add(new Product("Mũ Lưỡi Trai Đỏ", 100000, R.drawable.productseven));
        hat.add(new Product("Mũ Bảo Hiểm Xanh", 150000, R.drawable.productone));
        hat.add(new Product("Mũ Len Đen", 80000, R.drawable.productsix));

        // Thêm danh mục vào danh sách
        categoryList.add(new Category("Áo", R.drawable.productfour, shirts));
        categoryList.add(new Category("Quần", R.drawable.productseven, pants));
        categoryList.add(new Category("Áo khoác", R.drawable.productseven, hat));

        categoryAdapter = new CategoryAdapter(categoryList);
        recyclerViewCategories.setAdapter(categoryAdapter);

        // Thiết lập sự kiện click cho danh mục
        categoryAdapter.setOnItemClickListener(position -> {
            Category selectedCategory = categoryList.get(position);
            Intent intent = new Intent(getActivity(), ProductListActivity.class);
            // Truyền danh sách sản phẩm vào Intent
            intent.putParcelableArrayListExtra("productList", new ArrayList<>(selectedCategory.getProductList()));
            startActivity(intent);
        });

        return view;
    }
}
