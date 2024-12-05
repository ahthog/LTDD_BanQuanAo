package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class LoadFragment extends Fragment {

    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_load, container, false);

        // Khởi tạo ProgressBar
        progressBar = view.findViewById(R.id.progressBarDeterminate);

        // Bắt đầu quá trình tải
        loadData();

        return view;
    }

    private void loadData() {
        // Giả lập quá trình tải với Handler
        new Handler().postDelayed(() -> {
            // Chuyển sang LoginRegisterFragment sau khi tải xong
            if (getActivity() != null) {
                ((MainActivity) getActivity()).replaceFragment(new LoginRegisterFragment());
            }
        }, 3000); // Thời gian giả lập 3 giây
    }
}
