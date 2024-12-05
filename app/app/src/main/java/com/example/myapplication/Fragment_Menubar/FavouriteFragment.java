package com.example.myapplication.Fragment_Menubar;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.API.ApiServiceCategory;
import com.example.myapplication.API.ApiServiceFavProduct;
import com.example.myapplication.API.RetrofitClient;
import com.example.myapplication.Adapter.FavoriteProductAdapter;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteFragment extends Fragment {

    private RecyclerView recyclerViewFavorites;
    private FavoriteProductAdapter adapter;
    private List<Product> favoriteProducts;
    // Thêm biến để lưu mã người dùng (userId)
    private int userId = 1; // Đây là mã người dùng đã đăng nhập, có thể thay đổi tùy vào cách bạn lưu mã người dùng

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoriteProducts = new ArrayList<>(); // Khởi tạo danh sách sản phẩm
        adapter = new FavoriteProductAdapter(getContext(), favoriteProducts, 0); // Khởi tạo adapter với danh sách trống
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerViewFavorites = view.findViewById(R.id.recycler_view_favorite_products);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewFavorites.setLayoutManager(gridLayoutManager);

        // Gắn adapter vào RecyclerView
        recyclerViewFavorites.setAdapter(adapter);

        // Gọi API để lấy danh sách sản phẩm yêu thích của người dùng
        fetchFavoriteProducts();

        return view;
    }

    private void fetchFavoriteProducts() {
        // Cấu hình Retrofit
        ApiServiceFavProduct apiServiceFavProduct = RetrofitClient.getClient().create(ApiServiceFavProduct.class);

        // Gọi API để lấy sản phẩm yêu thích của người dùng
       apiServiceFavProduct.getAllFavProducts(userId).enqueue(new Callback<List<Product>>() {
           @Override
           public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
               if (response.isSuccessful() && response.body() != null) {
                   List<Product> favorite = response.body();
                   // Cập nhật danh sách và thông báo adapter
                   favoriteProducts.clear();
                   favoriteProducts.addAll(favorite);
                   adapter.notifyDataSetChanged(); // Cập nhật RecyclerView
               }
           }

           @Override
           public void onFailure(Call<List<Product>> call, Throwable t) {
               Log.e("API_ERROR", "Lỗi kết nối: " + t.getMessage());
               t.printStackTrace();
           }
       });


    }
}
