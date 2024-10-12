package com.example.testapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.testapplication.Fragment.LoadFragment;
import com.example.testapplication.Fragment_menubar.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout fragmentContainer; // Thêm biến cho FrameLayout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager2);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        fragmentContainer = findViewById(R.id.fragment_container);

        hideMainLayout();
        // Hiển thị LoadFragment ban đầu
        replaceFragment(new LoadFragment());

        // Thiết lập ViewPager và BottomNavigationView như trước
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                } else if (position == 1) {
                    bottomNavigationView.getMenu().findItem(R.id.menu_menu).setChecked(true);
                } else if (position == 2) {
                    bottomNavigationView.getMenu().findItem(R.id.menu_search).setChecked(true);
                } else if (position == 3) {
                    bottomNavigationView.getMenu().findItem(R.id.menu_favorite).setChecked(true);
                } else if (position == 4) {
                    bottomNavigationView.getMenu().findItem(R.id.menu_profile).setChecked(true);
                }

            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_home) {
                viewPager.setCurrentItem(0);
                return true;
            } else if (item.getItemId() == R.id.menu_menu) {
                viewPager.setCurrentItem(1);
                return true;
            } else if (item.getItemId() == R.id.menu_search) {
                viewPager.setCurrentItem(2);
                return true;
            } else if (item.getItemId() == R.id.menu_favorite) {
                viewPager.setCurrentItem(3);
                return true;
            } else if (item.getItemId() == R.id.menu_profile) {
                viewPager.setCurrentItem(4);
                return true;
            }else {
                return false;
            }
        });
    }
    // Phương thức để hiển thị ViewPager và BottomNavigationView
    public void showMainLayout() {
        viewPager.setVisibility(View.VISIBLE);
        bottomNavigationView.setVisibility(View.VISIBLE);
        fragmentContainer.setVisibility(View.GONE); // Ẩn FrameLayout chứa Fragment
    }

    // Phương thức để ẩn ViewPager và BottomNavigationView
    public void hideMainLayout() {
        viewPager.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
        fragmentContainer.setVisibility(View.VISIBLE); // Hiện FrameLayout chứa Fragment
    }

    // Phương thức để thay thế fragment
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment); // Đảm bảo rằng R.id.fragment_container là ID của ViewGroup chứa fragment
        transaction.addToBackStack(null); // Thêm fragment vào back stack
        transaction.commit();
    }

}



