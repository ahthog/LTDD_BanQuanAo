package com.example.testapplication.PurchaseHistory;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HistoryViewPagerAdapter extends FragmentStateAdapter {

    public HistoryViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new TabConfirmFragment();
        } else if (position == 1) {
            return new TabAwaitingPickupFragment();
        } else if (position == 2) {
            return new TaAwaitingShipmentFragment();
        } else if (position == 3) {
            return new TabDeliveredFragment();
        }else if (position == 4) {
            return new TabCancelledFragment();
        }else if (position == 5) {
            return new TabReturnedFragment();
        }else {
            return new TabConfirmFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }


}