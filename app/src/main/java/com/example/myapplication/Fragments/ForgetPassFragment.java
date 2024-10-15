package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class ForgetPassFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_pass, container, false);


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