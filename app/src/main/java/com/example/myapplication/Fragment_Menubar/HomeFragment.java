package com.example.myapplication.Fragment_Menubar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.BannerAdapter;
import com.example.myapplication.Adapter.ProductAdapter;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        // Ánh xạ ViewPager2 từ layout
        viewPagerBanner = view.findViewById(R.id.view_pager_banner);

        // Danh sách hình ảnh (ví dụ: từ drawable)
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

        // Thiết lập RecyclerView
        recyclerViewProducts = view.findViewById(R.id.recycler_view_products);
        productList1 = new ArrayList<>();
        productList1.add(new Product("Blue", "Beautiful blue dress", R.drawable.product1, "Doris Dress", "$50", 4.5f));
        productList1.add(new Product("White", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", R.drawable.product2, "Moon Shirt", "$35", 4.0f));
        productList1.add(new Product("Red", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", R.drawable.product3, "Ruby Dress", "$60", 4.8f));
        productList1.add(new Product("Black", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", R.drawable.product4, "Night Jacket", "$80", 4.3f));
        productList1.add(new Product("Green", "Casual green t-shirt", R.drawable.product5, "Leaf T-Shirt", "$25", 4.1f));
        productList1.add(new Product("Yellow", "Bright yellow summer dress", R.drawable.product6, "Sunshine Dress", "$55", 4.6f));
        // Tạo adapter cho RecyclerView
        ProductAdapter adapterA = new ProductAdapter(getContext(), productList1, 0); // Sử dụng getContext() để truyền context
        recyclerViewProducts.setAdapter(adapterA);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Thiết lập RecyclerView cho sản phẩm bán chạy
        recyclerViewProductsBest = view.findViewById(R.id.recycler_view_productbest);
        productList2 = new ArrayList<>();
        productList2.add(new Product("Blue", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", R.drawable.product1, "Doris Dress", "$50", 4.5f));
        productList2.add(new Product("White", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", R.drawable.product2, "Moon Shirt", "$35", 4.0f));
        productList2.add(new Product("Red", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", R.drawable.product3, "Ruby Dress", "$60", 4.8f));
        productList2.add(new Product("Black", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", R.drawable.product4, "Night Jacket", "$80", 4.3f));
        productList2.add(new Product("Green", "Casual green t-shirt", R.drawable.product5, "Leaf T-Shirt", "$25", 4.1f));
        productList2.add(new Product("Yellow", "Bright yellow summer dress", R.drawable.product6, "Sunshine Dress", "$55", 4.6f));

        // Tạo adapter cho RecyclerView bán chạy
        ProductAdapter adapterB = new ProductAdapter(getContext(), productList2, 1); // Sử dụng getContext() để truyền context
        recyclerViewProductsBest.setAdapter(adapterB);
        recyclerViewProductsBest.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return view;

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

    }


}