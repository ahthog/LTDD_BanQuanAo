package com.example.testapplication.Fragment_menubar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.testapplication.R;
import com.example.testapplication.home.HomeBannerAdapter;
import com.example.testapplication.home.HomeProductAdapter;
import com.example.testapplication.home.HomeProductBestAdapter;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager;
    private RecyclerView recyclerView, recyclerViewBest;

    // Các banner
    private final int[] banners = {
            R.drawable.bannerone,
            R.drawable.bannertwo,
            R.drawable.bannerthree
    };

    // Các sản phẩm
    private final Product[] products = {
            new Product(R.drawable.productone),
            new Product(R.drawable.producttwo),
            new Product(R.drawable.producthree),
            new Product(R.drawable.productfour),
            new Product(R.drawable.productfive),
            new Product(R.drawable.productsix),
            new Product(R.drawable.productseven)
    };

    // Các sản phẩm bán chạy
    private final Product[] bestSellingProducts = {
            new Product(R.drawable.productfive),
            new Product(R.drawable.productsix),
            new Product(R.drawable.productseven),
            new Product(R.drawable.productseven),
            new Product(R.drawable.productseven),
            new Product(R.drawable.productseven),
            new Product(R.drawable.productseven),
            new Product(R.drawable.productseven)

    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Tìm các view trong layout
        viewPager = view.findViewById(R.id.view_pager_banner);
        recyclerView = view.findViewById(R.id.recycler_view_products);
        recyclerViewBest = view.findViewById(R.id.recycler_view_productbest);


        // Thiết lập adapter cho ViewPager2 (banner)
        HomeBannerAdapter bannerAdapter = new HomeBannerAdapter(getContext(), banners);
        viewPager.setAdapter(bannerAdapter);

        // Thiết lập adapter cho RecyclerView (sản phẩm thông thường, ngang)
        HomeProductAdapter productAdapter = new HomeProductAdapter(getContext(), products);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(productAdapter);

        // Trong onCreateView hoặc onViewCreated của Fragment hoặc Activity của bạn
        recyclerViewBest = view.findViewById(R.id.recycler_view_productbest);

// Sử dụng GridLayoutManager với 2 cột
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewBest.setLayoutManager(gridLayoutManager);

// Thiết lập adapter
        HomeProductBestAdapter productbestAdapter = new HomeProductBestAdapter(getContext(), bestSellingProducts);
        recyclerViewBest.setAdapter(productbestAdapter);

        // Tự động chạy banner
        startAutoScroll();

        return view;
    }

    private void startAutoScroll() {
        // Tự động cuộn qua các banner
        viewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPager.getCurrentItem() == banners.length - 1) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
                viewPager.postDelayed(this, 3000); // Chạy lại sau 3 giây
            }
        }, 3000);
    }
}

