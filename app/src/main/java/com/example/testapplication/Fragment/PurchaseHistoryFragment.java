package com.example.testapplication.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;
import com.google.android.material.tabs.TabLayout;

public class PurchaseHistoryFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout cho fragment
        View view = inflater.inflate(R.layout.fragment_purchase_history, container, false);

        // Tìm các view trong layout
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        recyclerView = view.findViewById(R.id.recycler_view_orders);

        // Thiết lập tab và RecyclerView
        setupTabs(tabLayout);
        setupRecyclerView(recyclerView);

        return view;
    }

    private void setupTabs(TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText("Chờ xác nhận"));
        tabLayout.addTab(tabLayout.newTab().setText("Chờ lấy hàng"));
        tabLayout.addTab(tabLayout.newTab().setText("Chờ giao hàng"));
        tabLayout.addTab(tabLayout.newTab().setText("Trả hàng"));
        tabLayout.addTab(tabLayout.newTab().setText("Đã hủy"));
        tabLayout.addTab(tabLayout.newTab().setText("Đã giao"));
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Thiết lập adapter cho RecyclerView
        // recyclerView.setAdapter(new OrderAdapter(orderList));
    }
}
