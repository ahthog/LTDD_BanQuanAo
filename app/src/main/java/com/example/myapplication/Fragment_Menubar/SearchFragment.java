package com.example.myapplication.Fragment_Menubar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Adapter.ProductAdapter;
import com.example.myapplication.Adapter.SuggestionAdapter;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SearchFragment extends Fragment {
    private List<Product> products; // Danh sách sản phẩm
    private ProductAdapter searchAdapter; // Adapter cho kết quả tìm kiếm
    private SuggestionAdapter suggestionAdapter; // Adapter cho gợi ý sản phẩm
    private EditText searchInput;
    private RecyclerView recyclerViewSearch; // RecyclerView cho kết quả tìm kiếm
    private RecyclerView recyclerViewSuggestions; // RecyclerView cho gợi ý sản phẩm

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Khởi tạo các RecyclerView
        recyclerViewSearch = view.findViewById(R.id.recycler_view_search);
        recyclerViewSuggestions = view.findViewById(R.id.recycler_view_suggestions);

        searchInput = view.findViewById(R.id.search_input);
        Button searchButton = view.findViewById(R.id.search_button);

        // Khởi tạo GridLayoutManager cho cả hai RecyclerView
        recyclerViewSearch.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewSuggestions.setLayoutManager(new GridLayoutManager(getContext(), 2));

        searchAdapter = new ProductAdapter(getContext(), new ArrayList<>(), R.layout.item_product); // Adapter cho kết quả tìm kiếm
        suggestionAdapter = new SuggestionAdapter(getContext(), new ArrayList<>()); // Adapter cho gợi ý sản phẩm
        recyclerViewSearch.setAdapter(searchAdapter);
        recyclerViewSuggestions.setAdapter(suggestionAdapter);


        // Thiết lập TextWatcher cho EditText
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    // Hiển thị gợi ý tên sản phẩm khi người dùng gõ
                    filterNames(s.toString());
                } else {
                    // Ẩn RecyclerView gợi ý khi không có từ khóa
                    recyclerViewSuggestions.setVisibility(View.GONE);
                    recyclerViewSearch.setVisibility(View.GONE); // Ẩn kết quả tìm kiếm
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Xử lý sự kiện nhấn nút tìm kiếm
        searchButton.setOnClickListener(v -> searchProduct());

        // Xử lý sự kiện nhấn Enter trong EditText
        searchInput.setOnEditorActionListener((v, actionId, event) -> {
            if (event != null && event.getAction() == KeyEvent.ACTION_DOWN) {
                searchProduct();
                return true;
            }
            return false;
        });

        return view;
    }

    private void searchProduct() {
        String query = searchInput.getText().toString();
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredProducts.add(product);
            }
        }

        // Cập nhật adapter với danh sách sản phẩm đã lọc
        searchAdapter = new ProductAdapter(getContext(), filteredProducts, R.layout.item_product);
        recyclerViewSearch.setAdapter(searchAdapter);

        // Hiển thị hoặc ẩn RecyclerView kết quả tìm kiếm dựa trên danh sách sản phẩm đã lọc
        if (filteredProducts.isEmpty()) {
            recyclerViewSearch.setVisibility(View.GONE); // Ẩn nếu không có sản phẩm
        } else {
            recyclerViewSearch.setVisibility(View.VISIBLE); // Hiển thị nếu có sản phẩm
        }

        // Ẩn RecyclerView gợi ý
        recyclerViewSuggestions.setVisibility(View.GONE);
    }

    private void filterNames(String text) {
        // Lọc danh sách tên sản phẩm dựa trên từ khóa tìm kiếm
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredProducts.add(product);
            }
        }

        // Cập nhật adapter gợi ý với danh sách gợi ý
        suggestionAdapter = new SuggestionAdapter(getContext(), filteredProducts);
        recyclerViewSuggestions.setAdapter(suggestionAdapter);

        // Hiển thị hoặc ẩn RecyclerView gợi ý dựa trên danh sách gợi ý
        if (filteredProducts.isEmpty()) {
            recyclerViewSuggestions.setVisibility(View.GONE); // Ẩn nếu không có gợi ý
        } else {
            recyclerViewSuggestions.setVisibility(View.VISIBLE); // Hiển thị nếu có gợi ý
        }

        // Ẩn RecyclerView kết quả tìm kiếm khi có thay đổi trong ô tìm kiếm
        recyclerViewSearch.setVisibility(View.GONE); // Ẩn khi tìm kiếm gợi ý
    }


    public void setProducts(List<Product> products) {
        this.products = products; // Nhận danh sách sản phẩm
        if (searchAdapter != null) {
            // Cập nhật adapter với danh sách mới và layoutId
            searchAdapter = new ProductAdapter(getContext(), this.products, R.layout.item_product);
            recyclerViewSearch.setAdapter(searchAdapter);
        }
    }

}
