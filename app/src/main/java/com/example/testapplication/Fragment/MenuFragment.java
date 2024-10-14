package com.example.testapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Prepare data for MenuItems
        menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem("TOP", R.drawable.productfive));
        menuItemList.add(new MenuItem("PANT", R.drawable.productone));
        menuItemList.add(new MenuItem("SHOES", R.drawable.producttwo));
        menuItemList.add(new MenuItem("BAG", R.drawable.producttwo));
        menuItemList.add(new MenuItem("Sản phẩm 3", R.drawable.producttwo));
        menuItemList.add(new MenuItem("Sản phẩm 3", R.drawable.producttwo));
        menuItemList.add(new MenuItem("Sản phẩm 3", R.drawable.producttwo));

        // Set adapter
        menuAdapter = new MenuAdapter(getContext(), menuItemList);
        recyclerView.setAdapter(menuAdapter);

        return view;
    }
}
