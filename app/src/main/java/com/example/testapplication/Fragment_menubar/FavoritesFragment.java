package com.example.testapplication.Fragment_menubar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerViewFavorites;

    // Dữ liệu sản phẩm yêu thích (ví dụ)
    private final Product[] favoriteProducts = {
            new Product(R.drawable.productone, "Sản phẩm 1", "Đỏ", "500.000 VND"),
            new Product(R.drawable.producttwo, "Sản phẩm 2", "Xanh", "350.000 VND"),
            new Product(R.drawable.producthree, "Sản phẩm 3", "Vàng", "400.000 VND"),
            new Product(R.drawable.producthree, "Sản phẩm 3", "Vàng", "400.000 VND"),
            new Product(R.drawable.producthree, "Sản phẩm 3", "Vàng", "400.000 VND"),
            new Product(R.drawable.producthree, "Sản phẩm 3", "Vàng", "400.000 VND"),
            new Product(R.drawable.producthree, "Sản phẩm 3", "Vàng", "400.000 VND")
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

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
