package com.example.myapplication.Fragment_Menubar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.Fragments.CategoryDetailFragment;
import com.example.myapplication.Model.Category;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    private RecyclerView rcvData;
    private CategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // Initialize the RecyclerView using the view
        rcvData = view.findViewById(R.id.recyclerViewCategory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvData.setLayoutManager(linearLayoutManager);

        categoryAdapter = new CategoryAdapter(getContext(), getListCategory(), this::onClickGoToCategoryDetail);
        rcvData.setAdapter(categoryAdapter);
        return view;
    }

    private void onClickGoToCategoryDetail(Category category) {
        CategoryDetailFragment detailFragment = new CategoryDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_category", category); // Đảm bảo rằng Category implements Serializable
        bundle.putString("category_name", category.getName()); // Truyền tên danh mục
        detailFragment.setArguments(bundle); // Thiết lập đối số cho fragment

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailFragment) // Thay ID này bằng ID của ViewGroup bạn muốn thay thế
                .addToBackStack(null) // Thêm vào back stack để người dùng có thể quay lại
                .commit(); // Thực hiện giao dịch
    }

    private List<Category> getListCategory() {
        List<Category> list = new ArrayList<>();

        List<Product> aoProducts = new ArrayList<>();
        aoProducts.add(new Product("Áo sơ mi", R.drawable.product1));
        aoProducts.add(new Product("Áo thun", R.drawable.product2));
        aoProducts.add(new Product("Áo thun2", R.drawable.product3));
        aoProducts.add(new Product("Áo thun3", R.drawable.product4));
        aoProducts.add(new Product("Áo thun4", R.drawable.product5));
        aoProducts.add(new Product("Áo thun5", R.drawable.product6));
        aoProducts.add(new Product("Áo thun6", R.drawable.product7));


        List<Product> quanProducts = new ArrayList<>();
        quanProducts.add(new Product("Quần jean", R.drawable.product1));
        quanProducts.add(new Product("Quần shorts", R.drawable.product5));

        List<Product> vayProducts = new ArrayList<>();
        vayProducts.add(new Product("Váy xòe", R.drawable.product1));
        vayProducts.add(new Product("Váy ôm", R.drawable.product2));

        list.add(new Category("Áo", R.drawable.product6, aoProducts));
        list.add(new Category("Quần", R.drawable.product6, quanProducts));
        list.add(new Category("Váy", R.drawable.product5, vayProducts));

        list.add(new Category("Bộ", R.drawable.product4, new ArrayList<>())); // Thêm sản phẩm cho bộ nếu có

        return list;
    }
}
