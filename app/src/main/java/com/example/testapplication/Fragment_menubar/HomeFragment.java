package com.example.testapplication.Fragment_menubar;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapplication.DetailFragment.ProductDetailFragment;
import com.example.testapplication.R;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.home.HomeBannerAdapter;
import com.example.testapplication.home.ProductBestAdapter;
import com.example.testapplication.home.ProductNewAdapter;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager;
    private RecyclerView recyclerView, recyclerViewBest;

    private final int[] banners = { R.drawable.bannerone, R.drawable.bannertwo, R.drawable.bannerthree };
    private final Product[] products = {
            new Product(R.drawable.productone, "Product One", "Red", "A great product", "100 USD", 4.5f),
            new Product(R.drawable.producttwo, "Product Two", "Blue", "Another great product", "120 USD", 4.0f),
            new Product(R.drawable.productfive, "Product Five", "Green", "Best seller", "150 USD", 5.0f),
            new Product(R.drawable.productsix, "Product Six", "Yellow", "Top rated", "130 USD", 4.8f),
            new Product(R.drawable.productsix, "Product Six", "Yellow", "Top rated", "130 USD", 4.8f)
            // thêm các sản phẩm khác
    };

    private final Product[] bestSellingProducts = {
            new Product(R.drawable.productfive, "Product Five", "Green", "Best seller", "150 USD", 5.0f),
            new Product(R.drawable.productsix, "Product Six", "Yellow", "Top rated", "130 USD", 4.8f),
            new Product(R.drawable.productsix, "Product Six", "Yellow", "Top rated", "130 USD", 4.8f),
            new Product(R.drawable.productsix, "Product Six", "Yellow", "Top rated", "130 USD", 4.8f),
            new Product(R.drawable.productsix, "Product Six", "Yellow", "Top rated", "130 USD", 4.8f),
            new Product(R.drawable.productsix, "Product Six", "Yellow", "Top rated", "130 USD", 4.8f)
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.view_pager_banner);
        recyclerView = view.findViewById(R.id.recycler_view_products);
        recyclerViewBest = view.findViewById(R.id.recycler_view_productbest);

        // banner
        HomeBannerAdapter adapter = new HomeBannerAdapter(getContext(), banners);
        viewPager.setAdapter(adapter);

        // san pham moi
        ProductNewAdapter productAdapter = new ProductNewAdapter(getContext(), products);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(productAdapter);

        // san pham ban chay
        ProductBestAdapter productBestAdapter = new ProductBestAdapter(getContext(), bestSellingProducts);
        recyclerViewBest.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewBest.setAdapter(productBestAdapter);

        // Đặt sự kiện click cho các sản phẩm
        setProductClickListeners(productAdapter, productBestAdapter);

        // Auto scroll for banners
        startAutoScroll();

        return view;
    }

    private void setProductClickListeners(ProductNewAdapter productAdapter, ProductBestAdapter productBestAdapter) {
        // Sự kiện click sản phẩm từ danh sách mới
        productAdapter.setOnItemClickListener(product -> {
            // Tạo một đối tượng Bundle để truyền dữ liệu
            Bundle bundle = new Bundle();
            bundle.putParcelable("product", product);  // Sử dụng putParcelable thay vì putSerializable

            // Tạo một đối tượng ProductDetailFragment
            ProductDetailFragment productDetailFragment = new ProductDetailFragment();
            productDetailFragment.setArguments(bundle);  // Gắn dữ liệu vào Fragment

            // Điều hướng tới ProductDetailFragment
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, productDetailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // Sự kiện click sản phẩm từ danh sách bán chạy
        productBestAdapter.setOnItemClickListener(product -> {
            // Tạo một đối tượng Bundle để truyền dữ liệu
            Bundle bundle = new Bundle();
            bundle.putParcelable("product", product);  // Sử dụng putParcelable thay vì putSerializable

            // Tạo ProductDetailFragment và truyền dữ liệu qua Bundle
            ProductDetailFragment productDetailFragment = new ProductDetailFragment();
            productDetailFragment.setArguments(bundle);  // Gắn dữ liệu vào Fragment

            // Thay thế fragment hiện tại với Fragment chi tiết sản phẩm
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, productDetailFragment)
                    .addToBackStack(null)
                    .commit();
        });
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
                viewPager.postDelayed(this, 3000); // Run every 3 seconds
            }
        }, 3000);
    }
}