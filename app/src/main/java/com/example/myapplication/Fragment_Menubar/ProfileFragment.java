package com.example.myapplication.Fragment_Menubar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.Activity.HistoryPageActivity;
import com.example.myapplication.Activity.RewardsActivity;
import com.example.myapplication.R;

import java.util.Arrays;
import java.util.List;


public class ProfileFragment extends Fragment {
    private ListView orderHistoryListView;
    private List<String> orderStatusList;

    public ProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        orderHistoryListView = view.findViewById(R.id.orderHistoryListView);

        // Danh sách dữ liệu đơn hàng
        orderStatusList = Arrays.asList("Đơn hàng chờ xác nhận", "Đơn hàng đang giao",
                "Đơn hàng đã nhận", "Đơn hàng đã hủy", "Đơn hàng hoàn trả", "Đơn hàng hoàn trả");

        // Tạo ArrayAdapter và gán vào ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, orderStatusList);
        orderHistoryListView.setAdapter(adapter);
        TextView pointsSection = view.findViewById(R.id.pointsSection); // Nút hoặc mục "Điểm tích lũy"
        pointsSection.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RewardsActivity.class);
            startActivity(intent); // Mở RewardsActivity khi nhấn vào "Điểm tích lũy"
        });



        return view;
    }
}