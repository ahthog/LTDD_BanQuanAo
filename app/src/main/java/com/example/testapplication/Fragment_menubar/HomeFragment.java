package com.example.testapplication.Fragment_menubar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapplication.Model.Product;
import com.example.testapplication.R;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.Adapter.HomeBannerAdapter;
import com.example.testapplication.Adapter.HomeProductAdapter;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager;
    private RecyclerView recyclerView;

    // Các banner
    private final int[] banners = {
            R.drawable.bannerone,
            R.drawable.bannertwo,
            R.drawable.bannerthree
    };

    // Danh sách sản phẩm với chỉ hình ảnh
    private final Product[] products = {
            new Product(R.drawable.productone),
            new Product(R.drawable.producttwo),
            new Product(R.drawable.producthree),
            new Product(R.drawable.productfour),
            new Product(R.drawable.productfive),
            new Product(R.drawable.productsix),
            new Product(R.drawable.productseven)
            // Thêm các sản phẩm khác nếu cần
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.view_pager_banner);
        recyclerView = view.findViewById(R.id.recycler_view_products);

        // Thiết lập adapter cho ViewPager2
        HomeBannerAdapter adapter = new HomeBannerAdapter(getContext(), banners);
        viewPager.setAdapter(adapter);

        // Thiết lập adapter cho RecyclerView với chiều ngang
        HomeProductAdapter productAdapter = new HomeProductAdapter(getContext(), products);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(productAdapter);

        // Tự động chạy banner
        startAutoScroll();

        return view;
    }

    private void startAutoScroll() {
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
