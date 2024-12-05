package com.example.myapplication.Fragment_Menubar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.RetrofitClient;
import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.Adapter.ProductAdapter;
import com.example.myapplication.Fragments.CategoryDetailFragment;
import com.example.myapplication.Model.Category;
import com.example.myapplication.R;
import com.example.myapplication.API.ApiServiceCategory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryFragment extends Fragment {

    private RecyclerView rcvData;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;
    private CategoryAdapter CategoryAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // Initialize the RecyclerView
        rcvData = view.findViewById(R.id.recyclerViewCategory);
        rcvData.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the category list and adapter
        categoryList = new ArrayList<>();
        CategoryAdapter.OnCategoryClickListener listener = new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(Category category) {
                onClickGoToCategoryDetail(category);
            }
        };

        categoryAdapter = new CategoryAdapter(getContext(), categoryList, R.layout.item_category, listener);
        rcvData.setAdapter(categoryAdapter);

        // Fetch categories
        fetchProducts();

        return view;
    }


    private void fetchProducts() {
        // Cấu hình Retrofit
        ApiServiceCategory apiServiceCategory = RetrofitClient.getClient().create(ApiServiceCategory.class);
        apiServiceCategory.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Cập nhật danh sách và thông báo adapter
                    categoryList.clear();
                    categoryList.addAll(response.body());
                    categoryAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi kết nối: " + t.getMessage());

            }
        });


    }

    private void onClickGoToCategoryDetail(Category category) {
        // Handle category click event and navigate to CategoryDetailFragment
        CategoryDetailFragment detailFragment = new CategoryDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_category", category);
        detailFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit();
    }

}
