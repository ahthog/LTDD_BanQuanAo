package com.example.testapplication.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.testapplication.Fragment_menubar.Product;
import com.example.testapplication.PurchaseHistory.HistoryViewPagerAdapter;
import com.example.testapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PurchaseHistoryFragment extends Fragment {

    private RecyclerView recyclerView;


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
        View mView = inflater.inflate(R.layout.fragment_purchase_history, container, false);

        TabLayout tabLayout = mView.findViewById(R.id.tabLayout);
        ViewPager2 viewPager = mView.findViewById(R.id.view_pager_history);


        HistoryViewPagerAdapter adapter = new HistoryViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Sử dụng TabLayoutMediator để liên kết TabLayout với ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Chờ xác nhận");
            } else if (position == 1) {
                tab.setText("Chờ lấy hàng");
            } else if (position == 2) {
                tab.setText("Chờ giao hàng");
            } else if (position == 3) {
                tab.setText("Trả hàng");
            } else if (position == 4) {
                tab.setText("Đã giao");
            } else if (position == 5) {
                tab.setText("Đã hủy");
            }

        }).attach();

        return mView;
    }


}
