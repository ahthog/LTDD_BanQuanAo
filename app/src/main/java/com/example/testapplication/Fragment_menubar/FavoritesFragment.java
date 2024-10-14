package com.example.testapplication.Fragment_menubar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.Fragment_menubar.FavoriteProductAdapter;
import com.example.testapplication.Fragment_menubar.Product;
import com.example.testapplication.R;

public class FavoritesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private RecyclerView recyclerViewFavorites;
    // Dữ liệu sản phẩm yêu thích (ví dụ)
    private final Product[] favoriteProducts = {
            new Product(R.drawable.productsix, "NEGA PANTS", "Đỏ", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", "1.000.000 đ", 4.8f),
            new Product(R.drawable.productseven, "NEGA PANTS", "Xanh", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", "1.500.000 đ", 4.8f),
            new Product(R.drawable.producttwo, "NEGA PANTS", "Trắng", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", "2.500.000 đ", 4.8f),
            new Product(R.drawable.productsix, "NEGA PANTS", "Đen", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", "2.000.000 đ", 4.8f),
            new Product(R.drawable.producthree, "NEGA PANTS", "Đỏ", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", "1.600.000 đ", 4.8f),
            new Product(R.drawable.productsix, "NEGA PANTS", "Trắng", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", "2.000.000 đ", 4.8f),
            new Product(R.drawable.productone, "NEGA PANTS", "Đen", "Thiết kế thanh lịch, tôn dáng, phù hợp cho nhiều dịp. Màu sắc dễ phối, phong cách hiện đại.", "1.800.000 đ", 4.8f)
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