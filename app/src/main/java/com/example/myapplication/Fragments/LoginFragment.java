package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Fragment_Menubar.HomeFragment;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        ImageView btnBack = view.findViewById(R.id.imageback); // Đảm bảo có nút quay lại trong layout
        btnBack.setOnClickListener(v -> {
            // Quay lại LoginRegisterFragment
            if (getActivity() != null) {
                ((MainActivity) getActivity()).replaceFragment(new LoginRegisterFragment());
            }
        });

        Button btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            // Chuyển sang HomeFragment và hiển thị ViewPager
            if (getActivity() != null) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.replaceFragment(new HomeFragment()); // Chuyển sang HomeFragment
            }
        });

        TextView textView = view.findViewById(R.id.text_forget_pass);
        textView.setOnClickListener(v -> {
            // Chuyển sang LoginFragment
            if (getActivity() != null) {
                ((MainActivity) getActivity()).replaceFragment(new ForgetPassFragment());
            }
        });


        return view;
    }
}
