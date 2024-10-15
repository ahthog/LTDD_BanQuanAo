package com.example.testapplication.Fragments;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.DetailFragment.ProductAdapter;
import com.example.testapplication.Fragment_menubar.Product;
import com.example.testapplication.Adapter.SuggestionAdapter;
import com.example.testapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private Product[] products;
    private List<Product> filteredProducts = new ArrayList<>();
    private List<Product> suggestions = new ArrayList<>();
    private ProductAdapter productAdapter;
    private SuggestionAdapter suggestionAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewSuggestion;
    private EditText searchInput;
    private Button searchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Ánh xạ các thành phần
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerViewSuggestion = view.findViewById(R.id.recycler_view_suggestion);
        searchInput = view.findViewById(R.id.search_input);
        searchButton = view.findViewById(R.id.search_button);

        // Khởi tạo RecyclerView
        recyclerViewSuggestion.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Khởi tạo adapter
        productAdapter = new ProductAdapter(getContext(), filteredProducts);
        suggestionAdapter = new SuggestionAdapter(getContext(), suggestions, this::onSuggestionClick);

        // Lắng nghe sự thay đổi của EditText để hiển thị gợi ý
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Ẩn gợi ý nếu người dùng xóa ký tự trong trường tìm kiếm
                recyclerViewSuggestion.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterSuggestions(s.toString());

                // Hiển thị gợi ý khi người dùng nhập từ khóa
                if (s.length() > 0) {
                    recyclerViewSuggestion.setVisibility(View.VISIBLE); // Hiển thị gợi ý khi có từ khóa
                } else {
                    recyclerViewSuggestion.setVisibility(View.GONE); // Ẩn gợi ý nếu không có từ khóa
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Không cần làm gì trong afterTextChanged nếu không có yêu cầu đặc biệt
            }
        });


        // Lắng nghe sự kiện click của Button để thực hiện tìm kiếm
        searchButton.setOnClickListener(v -> {
            String keyword = searchInput.getText().toString();
            filterProducts(keyword);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerViewSuggestion.setVisibility(View.GONE); // Ẩn gợi ý sau khi tìm kiếm
        });

        // Lắng nghe sự kiện "Enter" trên bàn phím
        searchInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                String keyword = searchInput.getText().toString();
                filterProducts(keyword);recyclerView.setVisibility(View.VISIBLE);
                recyclerViewSuggestion.setVisibility(View.GONE); // Ẩn gợi ý sau khi nhấn Enter
                return true;
            }
            return false;
        });

        return view;
    }

    private void filterSuggestions(String text) {
        if (suggestions == null) {
            suggestions = new ArrayList<>();
        }
        suggestions.clear(); // Đảm bảo suggestions không phải null trước khi xóa
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                suggestions.add(product);
            }
        }
        recyclerViewSuggestion.setAdapter(suggestionAdapter);
        suggestionAdapter.notifyDataSetChanged();
    }


    private void filterProducts(String text) {
        if (filteredProducts == null) {
            filteredProducts = new ArrayList<>();
        }
        filteredProducts.clear(); // Đảm bảo filteredProducts không phải null trước khi xóa
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredProducts.add(product);
            }
        }
        recyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
        hideKeyboard();
    }

    private void hideKeyboard() {
        if (getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(searchInput.getWindowToken(), 0);
            }
        }
    }

    private void onSuggestionClick(Product product) {
        searchInput.setText(product.getName());
        filterProducts(product.getName());
        recyclerViewSuggestion.setVisibility(View.GONE); // Ẩn gợi ý khi chọn gợi ý
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}