package com.example.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.Adapter.CategoryAdapter;
import com.example.testapplication.Model.Category;
import com.example.testapplication.Model.Product;
import com.example.testapplication.ProductListActivity;
import com.example.testapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends Fragment {
    private RecyclerView recyclerViewCategories;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerViewCategories = view.findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext()));

        // Thiết lập sự kiện click cho danh mục
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Category selectedCategory = categoryList.get(position);
                Intent intent = new Intent(getActivity(), ProductListActivity.class);
                // Truyền danh sách sản phẩm vào Intent
                intent.putParcelableArrayListExtra("productList", new ArrayList<>(selectedCategory.getProductList()));
                startActivity(intent);
            }
        });

        return view;
    }
}
