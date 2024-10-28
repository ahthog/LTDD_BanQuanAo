package DatHang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.testapplication.R;

public class PhuongThucTT1Fragment extends Fragment {

    private RadioButton thanhtoankhinhan;
    private RadioButton lienketnganhang;
    private RadioButton momo;

    private TextView tongCongTextView;
    private TextView tongGiamTextView;
    private TextView thanhTienTextView;

    private static final int PHI_VAN_CHUYEN = 50000; // Phí vận chuyển mặc định

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

        // Khởi tạo các TextView
        tongCongTextView = view.findViewById(R.id.sotien2); // Đảm bảo ID này trùng với layout
        tongGiamTextView = view.findViewById(R.id.sotien3); // Đảm bảo ID này trùng với layout
        thanhTienTextView = view.findViewById(R.id.sotien5); // Đảm bảo ID này trùng với layout

        // Nhận dữ liệu từ Activity
        if (getArguments() != null) {
            int tongCong = getArguments().getInt("tongCong", 0);
            int tongGiam = getArguments().getInt("tongGiam", 0);
            int thanhTien = getArguments().getInt("thanhTien", 0);

            // Tính thành tiền cộng phí vận chuyển
            int thanhTienCuoi = thanhTien + PHI_VAN_CHUYEN;

            // Cập nhật giá trị cho các TextView
            tongCongTextView.setText(String.valueOf(tongCong));
            tongGiamTextView.setText(String.valueOf(tongGiam));
            thanhTienTextView.setText(String.valueOf(thanhTienCuoi)); // Hiển thị thành tiền cộng phí vận chuyển
        }

        // Thiết lập sự kiện cho mỗi RadioButton
        thanhtoankhinhan.setOnClickListener(v -> handleRadioButtonClick(thanhtoankhinhan));
        lienketnganhang.setOnClickListener(v -> handleRadioButtonClick(lienketnganhang));
        momo.setOnClickListener(v -> handleRadioButtonClick(momo));

        return view;
    }

    private void handleRadioButtonClick(RadioButton selectedRadioButton) {
        // Bỏ chọn các RadioButton còn lại
        thanhtoankhinhan.setChecked(selectedRadioButton == thanhtoankhinhan);
        lienketnganhang.setChecked(selectedRadioButton == lienketnganhang);
        momo.setChecked(selectedRadioButton == momo);
    }
}
