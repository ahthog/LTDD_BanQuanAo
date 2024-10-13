package com.example.testapplication.PurchaseHistory;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ProductAdapter extends FragmentStateAdapter {

    public ProductAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new TabOneFragment();
        } else if (position == 1) {
            return new TabTwoFragment();
        } else if (position == 2) {
            return new TabThreeFragment();
        } else if (position == 3) {
            return new TabFourFragment();
        }else if (position == 4) {
            return new TabFiveFragment();
        }else if (position == 5) {
            return new TabSixFragment();
        }else {
            return new TabOneFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }


}