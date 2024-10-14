package GioHang;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider; // Thêm import này
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapplication.R;

public class MatHang1Fragment extends Fragment {

    private LinearLayout linearLayoutMatHang;
    private View mathang1, mathang2, mathang3;
    private TextView delete1, delete2, delete3;
    private TextView numberDisplay1, numberDisplay2, numberDisplay3;
    private TextView tienGoc1, tienGoc2, tienGoc3;
    private TextView tienGiam1, tienGiam2, tienGiam3;
    private TextView tongTien1, tongTien2, tongTien3;
    private Button buttonTang1, buttonGiam1;
    private Button buttonTang2, buttonGiam2;
    private Button buttonTang3, buttonGiam3;
    private Spinner sizeSpinner; // Khai báo Spinner cho size1
    private Spinner sizeSpinner2; // Khai báo Spinner cho size2
    private Spinner sizeSpinner3; // Khai báo Spinner cho size3

    private CartViewModel cartViewModel; // Khai báo ViewModel

    private static final int GIA_MAT_HANG = 2700000;
    private static final int TIEN_GIAM = 474000;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mat_hang1, container, false);

        // Khởi tạo ViewModel
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        // Ánh xạ các phần tử trong giao diện
        linearLayoutMatHang = view.findViewById(R.id.linearLayoutMatHang);
        mathang1 = view.findViewById(R.id.mathang1);
        mathang2 = view.findViewById(R.id.mathang2);
        mathang3 = view.findViewById(R.id.mathang3);

        delete1 = view.findViewById(R.id.delete1);
        delete2 = view.findViewById(R.id.delete2);
        delete3 = view.findViewById(R.id.delete3);

        numberDisplay1 = view.findViewById(R.id.number_display1);
        buttonTang1 = view.findViewById(R.id.button_tang1);
        buttonGiam1 = view.findViewById(R.id.button_giam1);
        tienGoc1 = view.findViewById(R.id.tien_goc1);
        tienGiam1 = view.findViewById(R.id.tien_giam1);
        tongTien1 = view.findViewById(R.id.tong_tien1);

        numberDisplay2 = view.findViewById(R.id.number_display2);
        buttonTang2 = view.findViewById(R.id.button_tang2);
        buttonGiam2 = view.findViewById(R.id.button_giam2);
        tienGoc2 = view.findViewById(R.id.tien_goc2);
        tienGiam2 = view.findViewById(R.id.tien_giam2);
        tongTien2 = view.findViewById(R.id.tong_tien2);

        numberDisplay3 = view.findViewById(R.id.number_display3);
        buttonTang3 = view.findViewById(R.id.button_tang3);
        buttonGiam3 = view.findViewById(R.id.button_giam3);
        tienGoc3 = view.findViewById(R.id.tien_goc3);
        tienGiam3 = view.findViewById(R.id.tien_giam3);
        tongTien3 = view.findViewById(R.id.tong_tien3);

        // Khởi tạo Spinner
        sizeSpinner = view.findViewById(R.id.size1);
        sizeSpinner2 = view.findViewById(R.id.size2);
        sizeSpinner3 = view.findViewById(R.id.size3);

        // Tạo mảng kích thước
        String[] sizes = {"M", "L", "XL", "2XL"};

        // Tạo ArrayAdapter với layout mặc định và mảng kích thước
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Thiết lập adapter cho Spinner
        sizeSpinner.setAdapter(adapter);
        sizeSpinner2.setAdapter(adapter);
        sizeSpinner3.setAdapter(adapter);

        // Thiết lập sự kiện chọn cho Spinner
        setupSpinnerListeners();

        // Thiết lập sự kiện cho nút tăng và giảm số lượng
        buttonTang1.setOnClickListener(v -> {
            int currentValue = Integer.parseInt(numberDisplay1.getText().toString());
            numberDisplay1.setText(String.valueOf(currentValue + 1));
            updateTienGoc();
            updateTongTien();
        });

        buttonGiam1.setOnClickListener(v -> {
            int currentValue = Integer.parseInt(numberDisplay1.getText().toString());
            if (currentValue > 1) {
                numberDisplay1.setText(String.valueOf(currentValue - 1));
                updateTienGoc();
                updateTongTien();
            }
        });

        // Mặt hàng 2
        buttonTang2.setOnClickListener(v -> {
            int currentValue = Integer.parseInt(numberDisplay2.getText().toString());
            numberDisplay2.setText(String.valueOf(currentValue + 1));
            updateTienGoc();
            updateTongTien();
        });



        buttonGiam2.setOnClickListener(v -> {
            int currentValue = Integer.parseInt(numberDisplay2.getText().toString());
            if (currentValue > 1) {
                numberDisplay2.setText(String.valueOf(currentValue - 1));
                updateTienGoc();
                updateTongTien();
            }
        });

        // Mặt hàng 3
        buttonTang3.setOnClickListener(v -> {
            int currentValue = Integer.parseInt(numberDisplay3.getText().toString());
            numberDisplay3.setText(String.valueOf(currentValue + 1));
            updateTienGoc();
            updateTongTien();
        });

        buttonGiam3.setOnClickListener(v -> {
            int currentValue = Integer.parseInt(numberDisplay3.getText().toString());
            if (currentValue > 1) {
                numberDisplay3.setText(String.valueOf(currentValue - 1));
                updateTienGoc();
                updateTongTien();
            }
        });

        // Xóa mặt hàng
        delete1.setOnClickListener(v -> {
            linearLayoutMatHang.removeView(mathang1);
            linearLayoutMatHang.requestLayout();
            // Đặt dữ liệu mặt hàng 1 về 0 trong ViewModel
            cartViewModel.setTienGoc1(0);
            cartViewModel.setTienGiam1(0);
            cartViewModel.setTongTien1(0);
            updateTongTien(); // Cập nhật lại tổng tiền sau khi xóa
        });

        delete2.setOnClickListener(v -> {
            linearLayoutMatHang.removeView(mathang2);
            linearLayoutMatHang.requestLayout();
            // Đặt dữ liệu mặt hàng 2 về 0 trong ViewModel
            cartViewModel.setTienGoc2(0);
            cartViewModel.setTienGiam2(0);
            cartViewModel.setTongTien2(0);
            updateTongTien(); // Cập nhật lại tổng tiền sau khi xóa
        });

        delete3.setOnClickListener(v -> {
            linearLayoutMatHang.removeView(mathang3);
            linearLayoutMatHang.requestLayout();
            // Đặt dữ liệu mặt hàng 3 về 0 trong ViewModel
            cartViewModel.setTienGoc3(0);
            cartViewModel.setTienGiam3(0);
            cartViewModel.setTongTien3(0);
            updateTongTien(); // Cập nhật lại tổng tiền sau khi xóa
        });



        return view;
    }

    private void setupSpinnerListeners() {
        // Sự kiện chọn cho sizeSpinner
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = (String) parent.getItemAtPosition(position);
                Toast.makeText(getContext(), "Kích thước 1 đã chọn: " + selectedSize, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì cả nếu không có gì được chọn
            }
        });

        // Sự kiện chọn cho sizeSpinner2
        sizeSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = (String) parent.getItemAtPosition(position);
                Toast.makeText(getContext(), "Kích thước 2 đã chọn: " + selectedSize, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì cả nếu không có gì được chọn
            }
        });

        // Sự kiện chọn cho sizeSpinner3
        sizeSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = (String) parent.getItemAtPosition(position);
                Toast.makeText(getContext(), "Kích thước 3 đã chọn: " + selectedSize, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì cả nếu không có gì được chọn
            }
        });
    }

    private void updateCartTotal() {
        int tongGoc = cartViewModel.getTienGoc1().getValue() + cartViewModel.getTienGoc2().getValue() + cartViewModel.getTienGoc3().getValue();
        int tongGiam = cartViewModel.getTienGiam1().getValue() + cartViewModel.getTienGiam2().getValue() + cartViewModel.getTienGiam3().getValue();
        int tongTien = tongGoc - tongGiam;

        // Cập nhật lại tổng tiền trong ViewModel
        cartViewModel.setTongTien1(tongTien); // Hoặc sử dụng setTongTien cho tổng giỏ hàng
    }

    private void updateTienGoc() {
        int soLuong1 = Integer.parseInt(numberDisplay1.getText().toString());
        int soLuong2 = Integer.parseInt(numberDisplay2.getText().toString());
        int soLuong3 = Integer.parseInt(numberDisplay3.getText().toString());

        // Cập nhật giá trị vào ViewModel
        int goc1 = GIA_MAT_HANG * soLuong1;
        int goc2 = GIA_MAT_HANG * soLuong2;
        int goc3 = GIA_MAT_HANG * soLuong3;

        cartViewModel.setTienGoc1(goc1);
        cartViewModel.setTienGoc2(goc2);
        cartViewModel.setTienGoc3(goc3);

        // Cập nhật giao diện
        tienGoc1.setText(String.valueOf(goc1));
        tienGoc2.setText(String.valueOf(goc2));
        tienGoc3.setText(String.valueOf(goc3));

        // Cập nhật tiền giảm
        updateTienGiam();

        // Cập nhật tổng tiền
        updateTongTien();
    }

    private void updateTienGiam() {
        int soLuong1 = Integer.parseInt(numberDisplay1.getText().toString());
        int soLuong2 = Integer.parseInt(numberDisplay2.getText().toString());
        int soLuong3 = Integer.parseInt(numberDisplay3.getText().toString());

        // Cập nhật giá trị vào ViewModel
        int giam1 = TIEN_GIAM * soLuong1;
        int giam2 = TIEN_GIAM * soLuong2;
        int giam3 = TIEN_GIAM * soLuong3;

        cartViewModel.setTienGiam1(giam1);
        cartViewModel.setTienGiam2(giam2);
        cartViewModel.setTienGiam3(giam3);

        // Cập nhật giao diện
        tienGiam1.setText(String.valueOf(giam1));
        tienGiam2.setText(String.valueOf(giam2));
        tienGiam3.setText(String.valueOf(giam3));
    }

    private void updateTongTien() {
        int goc1 = cartViewModel.getTienGoc1().getValue() != null ? cartViewModel.getTienGoc1().getValue() : 0;
        int goc2 = cartViewModel.getTienGoc2().getValue() != null ? cartViewModel.getTienGoc2().getValue() : 0;
        int goc3 = cartViewModel.getTienGoc3().getValue() != null ? cartViewModel.getTienGoc3().getValue() : 0;

        // Tính lại tổng tiền sau khi xóa hoặc thay đổi số lượng
        cartViewModel.setTongTien1(goc1 - (cartViewModel.getTienGiam1().getValue() != null ? cartViewModel.getTienGiam1().getValue() : 0));
        cartViewModel.setTongTien2(goc2 - (cartViewModel.getTienGiam2().getValue() != null ? cartViewModel.getTienGiam2().getValue() : 0));
        cartViewModel.setTongTien3(goc3 - (cartViewModel.getTienGiam3().getValue() != null ? cartViewModel.getTienGiam3().getValue() : 0));

        // Cập nhật giao diện
        tongTien1.setText(String.valueOf(cartViewModel.getTongTien1().getValue()));
        tongTien2.setText(String.valueOf(cartViewModel.getTongTien2().getValue()));
        tongTien3.setText(String.valueOf(cartViewModel.getTongTien3().getValue()));
    }

}
