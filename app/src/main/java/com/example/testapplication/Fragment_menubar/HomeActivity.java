package com.example.testapplication.Fragment_menubar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.testapplication.DetailFragment.ProductDetailFragment;
import com.example.testapplication.GioHang.GioHangActivity;
import com.example.testapplication.R;
import com.example.testapplication.home.HomeBannerAdapter;
import com.example.testapplication.home.ProductBestAdapter;
import com.example.testapplication.home.ProductNewAdapter;

public class HomeActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home); // Sử dụng layout đã tạo cho HomeActivity

        viewPager = findViewById(R.id.view_pager_banner);
        recyclerView = findViewById(R.id.recycler_view_products);
        recyclerViewBest = findViewById(R.id.recycler_view_productbest);
        ImageView cartIcon = findViewById(R.id.cart_icon);

        // Thiết lập ViewPager cho banner
        HomeBannerAdapter adapter = new HomeBannerAdapter(this, banners);
        viewPager.setAdapter(adapter);

        // Thiết lập RecyclerView cho sản phẩm mới
        ProductNewAdapter productAdapter = new ProductNewAdapter(this, products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(productAdapter);

        // Thiết lập RecyclerView cho sản phẩm bán chạy
        ProductBestAdapter productBestAdapter = new ProductBestAdapter(this, bestSellingProducts);
        recyclerViewBest.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewBest.setAdapter(productBestAdapter);

        // Đặt sự kiện click cho các sản phẩm
        setProductClickListeners(productAdapter, productBestAdapter);

        // Đặt sự kiện click cho biểu tượng giỏ hàng
        setCartIconClickListener(cartIcon);

        // Auto scroll for banners
        startAutoScroll();
    }

    private void setProductClickListeners(ProductNewAdapter productAdapter, ProductBestAdapter productBestAdapter) {
        // Sự kiện click sản phẩm từ danh sách mới
        productAdapter.setOnItemClickListener(product -> {
            // Tạo một đối tượng Bundle để truyền dữ liệu
            Bundle bundle = new Bundle();
            bundle.putParcelable("product", product);

            // Tạo một đối tượng ProductDetailFragment
            ProductDetailFragment productDetailFragment = new ProductDetailFragment();
            productDetailFragment.setArguments(bundle);

            // Điều hướng tới ProductDetailFragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, productDetailFragment) // Thay đổi theo cách mà bạn muốn quản lý fragment
                    .addToBackStack(null)
                    .commit();
        });

        // Sự kiện click sản phẩm từ danh sách bán chạy
        productBestAdapter.setOnItemClickListener(product -> {
            // Tạo một đối tượng Bundle để truyền dữ liệu
            Bundle bundle = new Bundle();
            bundle.putParcelable("product", product);

            // Tạo ProductDetailFragment và truyền dữ liệu qua Bundle
            ProductDetailFragment productDetailFragment = new ProductDetailFragment();
            productDetailFragment.setArguments(bundle);

            // Thay thế fragment hiện tại với Fragment chi tiết sản phẩm
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, productDetailFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    private void setCartIconClickListener(ImageView cartIcon) {
        cartIcon.setOnClickListener(v -> {
            // Chuyển sang GioHangActivity khi nhấn vào icon giỏ hàng
            Intent intent = new Intent(this, GioHangActivity.class);
            startActivity(intent);
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
                viewPager.postDelayed(this, 3000); // Chạy mỗi 3 giây
            }
        }, 3000);
    }
}
