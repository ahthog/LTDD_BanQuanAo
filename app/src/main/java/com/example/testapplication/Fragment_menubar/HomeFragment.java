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
import com.example.testapplication.home.ProductNewAdapter;
import com.example.testapplication.home.ProductBestAdapter;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager;
    private RecyclerView recyclerView, recyclerViewBest;

    // Các banner
    private final int[] banners = {
            R.drawable.bannerone,
            R.drawable.bannertwo,
            R.drawable.bannerthree
    };

    // Mảng sản phẩm mới (ảnh sản phẩm)
    private Product[] products = {
            new Product(R.drawable.productone, "Product 1", "Red", "Description of Product 1", "$100", 4.5f),
            new Product(R.drawable.producttwo, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.producthree, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.productseven, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.producttwo, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.producttwo, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.productsix, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.producttwo, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.productfour, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f)
            // Các sản phẩm mới khác
    };

    // Mảng sản phẩm bán chạy (tên và ảnh sản phẩm)
    private Product[] bestSellingProducts = {
            new Product(R.drawable.productsix, "Product 1", "Red", "Description of Product 1", "$100", 4.5f),
            new Product(R.drawable.producthree, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.productfive, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.producttwo, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.productone, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.producttwo, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.producthree, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.producttwo, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f),
            new Product(R.drawable.productsix, "Product 2", "Blue", "Description of Product 2", "$150", 4.0f)
            // Các sản phẩm bán chạy khác
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
        ProductNewAdapter productAdapter = new ProductNewAdapter(getContext(), products);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(productAdapter);

        // Trong onCreateView hoặc onViewCreated của Fragment hoặc Activity của bạn
        recyclerViewBest = view.findViewById(R.id.recycler_view_productbest);

// Sử dụng GridLayoutManager với 2 cột
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewBest.setLayoutManager(gridLayoutManager);

// Thiết lập adapter
        ProductBestAdapter productbestAdapter = new ProductBestAdapter(getContext(), bestSellingProducts);
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