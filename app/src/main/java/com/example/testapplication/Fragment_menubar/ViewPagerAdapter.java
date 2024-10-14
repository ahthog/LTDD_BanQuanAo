package com.example.testapplication.Fragment_menubar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.testapplication.Fragment.MenuFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {

        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new HomeFragment();
        } else if (position == 1) {
            return new MenuFragment();
        } else if (position == 2) {
            return new SearchFragment();
        } else if (position == 3) {
            return new FavoritesFragment();
        } else if (position == 4) {
            return new ProfileFragment();
        } else {
            return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {

        return 5;
    }
}
