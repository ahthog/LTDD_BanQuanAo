package com.example.myapplication.Fragment_Menubar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.API.ApiService;
import com.example.myapplication.API.RetrofitClient;
import com.example.myapplication.Activity.CartActivity;
import com.example.myapplication.Adapter.BannerAdapter;
import com.example.myapplication.Adapter.ProductAdapter;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPagerBanner;
    private BannerAdapter adapter;
    private List<Integer> bannerImages;
    private Handler bannerHandler = new Handler(Looper.getMainLooper());
    private RecyclerView recyclerViewProducts;
    private RecyclerView recyclerViewProductsBest;

    private ProductAdapter productAdapter1;
    private ProductAdapter productAdapter2;
    private List<Product> productList1;
    private List<Product> productList2;
    private ImageView cartIcon;

    private Runnable bannerRunnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = viewPagerBanner.getCurrentItem();
            int nextItem = (currentItem + 1) % bannerImages.size();
            viewPagerBanner.setCurrentItem(nextItem, true);
            bannerHandler.postDelayed(this, 1500); // Chuyển sau 2 giây
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Tham chiếu đến icon giỏ hàng
        cartIcon = view.findViewById(R.id.cart_icon);

        // Đặt sự kiện OnClickListener
        cartIcon.setOnClickListener(v -> {
            // Chuyển đến CartActivity
            Intent intent = new Intent(getActivity(), CartActivity.class);
            startActivity(intent);
        });

        // Ánh xạ ViewPager2 từ layout
        viewPagerBanner = view.findViewById(R.id.view_pager_banner);

        // Danh sách hình ảnh banner (ví dụ: từ drawable)
        bannerImages = Arrays.asList(
                R.drawable.banner1,
                R.drawable.banner2,
                R.drawable.banner3
        );

        // Khởi tạo adapter và gán cho ViewPager2
        adapter = new BannerAdapter(bannerImages);
        viewPagerBanner.setAdapter(adapter);

        // Bắt đầu tự động chuyển trang
        bannerHandler.postDelayed(bannerRunnable, 2000);

        //-------------------
        // Thiết lập RecyclerView cho các sản phẩm mới
        recyclerViewProducts = view.findViewById(R.id.recycler_view_products);
        productList1 = new ArrayList<>();
        productAdapter1 = new ProductAdapter(getContext(), productList1, 0); // Khởi tạo adapter với danh sách trống
        recyclerViewProducts.setAdapter(productAdapter1);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Thiết lập RecyclerView cho các sản phẩm bán chạy
        recyclerViewProductsBest = view.findViewById(R.id.recycler_view_productbest);
        productList2 = new ArrayList<>();
        productAdapter2 = new ProductAdapter(getContext(), productList2, 1); // Khởi tạo adapter cho sản phẩm bán chạy
        recyclerViewProductsBest.setAdapter(productAdapter2);
        recyclerViewProductsBest.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Gọi API để lấy dữ liệu sản phẩm
        fetchProducts();

        return view;
    }

    private void fetchProducts() {
        // Cấu hình Retrofit để gọi API

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.getAllProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body();

                    // Cập nhật danh sách sản phẩm vào RecyclerView
                    productList1.clear();
                    productList1.addAll(products);
                    productAdapter1.notifyDataSetChanged(); // Cập nhật lại adapter sau khi có dữ liệu mới

                    // Cập nhật danh sách sản phẩm bán chạy vào RecyclerView thứ hai (nếu có)
                    productList2.clear();
                    productList2.addAll(products);  // Lấy thêm sản phẩm cho danh sách bán chạy
                    productAdapter2.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi kết nối: " + t.getMessage());

            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        // Dừng tự động chuyển trang khi Fragment không hiển thị
        bannerHandler.removeCallbacks(bannerRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Tiếp tục tự động chuyển trang khi Fragment hiển thị trở lại
        bannerHandler.postDelayed(bannerRunnable, 2000);
    }
}
