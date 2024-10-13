package com.example.testapplication.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.testapplication.MainActivity;
import com.example.testapplication.R;

public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        ImageView btnBack = view.findViewById(R.id.imageback); // Đảm bảo có nút quay lại trong layout
        btnBack.setOnClickListener(v -> {
            // Quay lại LoginRegisterFragment
            if (getActivity() != null) {
                ((MainActivity) getActivity()).replaceFragment(new LoginRegisterFragment());
            }
        });

        return view;
    }
}