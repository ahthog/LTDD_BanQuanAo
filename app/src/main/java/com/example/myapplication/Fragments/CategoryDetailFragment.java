package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.ApiService;
import com.example.myapplication.Adapter.ProductAdapter;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class CategoryDetailFragment extends Fragment {
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    public CategoryDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_detail, container, false);

        recyclerViewProducts = view.findViewById(R.id.rcvCategoryDetail);
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Nhận categoryId từ Bundle
        if (getArguments() != null) {
            int categoryId = getArguments().getInt("categoryId");
            loadProductsByCategory(categoryId); // Lấy sản phẩm theo categoryId
        }

        return view;
    }

    private void loadProductsByCategory(int categoryId) {
        // Khởi tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.21.155:8081/appadroidbanquanao/") // Thay bằng URL của API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Product>> call = apiService.getProductsByCategory(categoryId);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Lấy danh sách sản phẩm từ API
                    productList = response.body();
                    // Khởi tạo và gán adapter cho RecyclerView
                    productAdapter = new ProductAdapter(getContext(), productList, 0);
                    recyclerViewProducts.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Xử lý khi gọi API thất bại
                t.printStackTrace();
            }
        });
    }
}