package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.ProductAdapter;
import com.example.myapplication.Model.Category;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.util.Arrays;
import java.util.List;

public class CategoryDetailFragment extends Fragment {

    private RecyclerView rcvProducts;
    private ProductAdapter productAdapter;
    private TextView tvNameCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_detail, container, false);

        // Nhận dữ liệu từ Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            Category category = (Category) bundle.getSerializable("object_category");
            String categoryName = bundle.getString("category_name"); // Nhận tên danh mục

            tvNameCategory = view.findViewById(R.id.tv_name_category);
            tvNameCategory.setText(categoryName);
            rcvProducts = view.findViewById(R.id.rcvCategoryDetail); // ID của RecyclerView trong layout

            // Khởi tạo RecyclerView
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            rcvProducts.setLayoutManager(layoutManager);
            rcvProducts.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 cột

            if (category != null) {
                // Chuyển đổi List<Product> thành Product[]
                List<Product> productList = category.getProductList();
                Product[] productArray = productList.toArray(new Product[0]); // Chuyển đổi sang mảng

                productAdapter = new ProductAdapter(getContext(), Arrays.asList(productArray));
                rcvProducts.setAdapter(productAdapter);
            }
        }

        return view;
    }
}
