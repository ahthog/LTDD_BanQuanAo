package com.example.testapplication.DatHang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.testapplication.R;

public class PhuongThucTT1Fragment extends Fragment {

    private RadioButton thanhtoankhinhan;
    private RadioButton lienketnganhang;
    private RadioButton momo;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phuong_thuc_t_t1, container, false);

        // Khởi tạo các RadioButton
        thanhtoankhinhan = view.findViewById(R.id.thanhtoankhinhan);
        lienketnganhang = view.findViewById(R.id.lienketnganhang);
        momo = view.findViewById(R.id.momo);

        // Đặt mặc định là "Thanh toán khi nhận hàng" được chọn
        thanhtoankhinhan.setChecked(true);

        // Thiết lập sự kiện cho mỗi RadioButton
        thanhtoankhinhan.setOnClickListener(v -> handleRadioButtonClick(thanhtoankhinhan));
        lienketnganhang.setOnClickListener(v -> handleRadioButtonClick(lienketnganhang));
        momo.setOnClickListener(v -> handleRadioButtonClick(momo));

        return view;
    }

    private void handleRadioButtonClick(RadioButton selectedRadioButton) {
        // Bỏ chọn các RadioButton còn lại
        if (selectedRadioButton != thanhtoankhinhan) {
            thanhtoankhinhan.setChecked(false);
        }
        if (selectedRadioButton != lienketnganhang) {
            lienketnganhang.setChecked(false);
        }
        if (selectedRadioButton != momo) {
            momo.setChecked(false);
        }

    }
}
