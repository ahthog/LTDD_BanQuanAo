package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Fragment_Menubar.CategoryFragment;
import com.example.myapplication.Fragment_Menubar.FavouriteFragment;
import com.example.myapplication.Fragment_Menubar.HomeFragment;
import com.example.myapplication.Fragment_Menubar.ProfileFragment;
import com.example.myapplication.Fragment_Menubar.SearchFragment;
import com.example.myapplication.Fragments.LoadFragment;
import com.example.myapplication.Model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Product> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = new ArrayList<>();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.menu_home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.menu_category) {
                selectedFragment = new CategoryFragment();
            } else if (item.getItemId() == R.id.menu_search) {
                SearchFragment searchFragment = new SearchFragment();
                searchFragment.setProducts(productList); // Truyền danh sách sản phẩm vào SearchFragment
                selectedFragment = searchFragment;
            } else if (item.getItemId() == R.id.menu_favourite) {
                selectedFragment = new FavouriteFragment();
            } else if (item.getItemId() == R.id.menu_profile) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }

            return true;
        });

        // Mặc định hiển thị HomeFragment
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.menu_home);
        }

    }

    // Phương thức để thay thế fragment
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment); // Đảm bảo rằng R.id.fragment_container là ID của ViewGroup chứa fragment
        transaction.addToBackStack(null); // Thêm fragment vào back stack
        transaction.commit();
    }



}