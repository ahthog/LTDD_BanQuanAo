package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class LoginRegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_register, container, false);

        Button btnLogin = view.findViewById(R.id.btnLogin);
        Button btnRegister = view.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v -> {
            // Chuyển sang LoginFragment
            if (getActivity() != null) {
                ((MainActivity) getActivity()).replaceFragment(new LoginFragment());
            }
        });

        btnRegister.setOnClickListener(v -> {
            // Chuyển sang RegisterFragment
            if (getActivity() != null) {
                ((MainActivity) getActivity()).replaceFragment(new RegisterFragment());
            }
        });

        return view;
    }
}
