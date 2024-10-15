package com.example.myapplication.Fragment_Menubar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.FavoriteProductAdapter;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;


public class FavouriteFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private RecyclerView recyclerViewFavorites;
    // Dữ liệu sản phẩm yêu thích (ví dụ)
    private final Product[] favoriteProducts = {
            new Product("Đỏ", "Quần NEGA PANTS với thiết kế thanh lịch, tôn dáng, hoàn hảo cho các dịp đặc biệt.", R.drawable.product1, "NEGA PANTS", "1.000.000 đ", 4.8f),
            new Product("Xanh", "Quần NEGA PANTS với thiết kế hiện đại, dễ phối đồ cho mọi phong cách.", R.drawable.product2, "NEGA PANTS", "1.500.000 đ", 4.8f),
            new Product("Trắng", "Quần NEGA PANTS trắng, thiết kế thanh lịch, phù hợp với mọi bối cảnh.", R.drawable.product3, "NEGA PANTS", "2.500.000 đ", 4.8f),
            new Product("Đen", "Quần NEGA PANTS đen, phong cách đơn giản, dễ dàng kết hợp với nhiều trang phục.", R.drawable.product4, "NEGA PANTS", "2.000.000 đ", 4.8f),
            new Product("Xám", "Quần NEGA PANTS xám, thiết kế thanh lịch, lý tưởng cho văn phòng và tiệc tùng.", R.drawable.product5, "NEGA PANTS", "1.600.000 đ", 4.8f),
            new Product("Nâu", "Quần NEGA PANTS nâu, tạo nên sự khác biệt trong phong cách hàng ngày.", R.drawable.product6, "NEGA PANTS", "2.000.000 đ", 4.8f),
            new Product("Xanh quân đội", "Quần NEGA PANTS xanh quân đội, phong cách trẻ trung, phù hợp cho các hoạt động ngoài trời.", R.drawable.product7, "NEGA PANTS", "1.800.000 đ", 4.8f)
    };


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        // Tìm RecyclerView
        recyclerViewFavorites = view.findViewById(R.id.recycler_view_favorite_products);
        // Thiết lập GridLayoutManager với 2 cột
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewFavorites.setLayoutManager(gridLayoutManager);
        // Thiết lập Adapter cho RecyclerView
        FavoriteProductAdapter adapter = new FavoriteProductAdapter(getContext(), favoriteProducts);
        recyclerViewFavorites.setAdapter(adapter);
        return view;

    }
}