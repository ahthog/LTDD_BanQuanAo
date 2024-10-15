package com.example.testapplication.DatHang;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testapplication.GioHang.GioHangActivity;
import com.example.testapplication.R;

public class DatHangActivity extends AppCompatActivity {

    private Button confirmButton;
    private Button buttonXacNhan; // Nút xác nhận trong dialog
    private View overlay;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang); // Đảm bảo bạn đang đặt layout chính

        // Khởi tạo các thành phần giao diện
        confirmButton = findViewById(R.id.button2); // Nút xác nhận
        overlay = findViewById(R.id.overlay); // View overlay
        ImageView imageBack = findViewById(R.id.imageback); // Nút quay lại
        TextView nhapDiaChi = findViewById(R.id.nhapdiachi1); // TextView nhập địa chỉ

        // Xử lý sự kiện nhấn nút quay lại
        imageBack.setOnClickListener(v -> {
            // Quay lại GioHangActivity
            startActivity(new Intent(DatHangActivity.this, GioHangActivity.class));
            finish(); // Kết thúc DatHangActivity để không quay lại trang này
        });

        // Xử lý sự kiện nhấn vào TextView nhập địa chỉ
        nhapDiaChi.setOnClickListener(v -> {
            showAddressInputDialog(); // Hiển thị dialog nhập địa chỉ
        });

        confirmButton.setOnClickListener(v -> {
            // Làm mờ layout chính
            overlay.setVisibility(View.VISIBLE);
            // Hiển thị dialog với thông báo nhập mã PIN
            showPinDialog();
        });
    }

    private void showAddressInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_nhap_dia_chi, null); // Tạo layout cho dialog

        // Thêm layout vào dialog
        builder.setView(dialogView);

        EditText editTextDiaChi = dialogView.findViewById(R.id.editTextDiaChi); // EditText nhập địa chỉ
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String diaChi = editTextDiaChi.getText().toString().trim(); // Lấy địa chỉ nhập vào và loại bỏ khoảng trắng
                if (!diaChi.isEmpty()) { // Kiểm tra nếu địa chỉ không rỗng
                    // Cập nhật TextView với địa chỉ đã nhập
                    TextView nhapDiaChi = findViewById(R.id.nhapdiachi1);
                    nhapDiaChi.setText(diaChi);
                    // Xử lý địa chỉ đã nhập, có thể lưu vào biến hoặc thực hiện logic khác
                    Toast.makeText(DatHangActivity.this, "Địa chỉ: " + diaChi, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DatHangActivity.this, "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show(); // Thông báo nếu địa chỉ rỗng
                }
            }
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void showPinDialog() {
        // Tạo dialog mới
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_thong_bao, null); // Đảm bảo layout cho dialog đã được tạo

        // Thêm layout vào dialog
        builder.setView(dialogView);

        // Xử lý các EditText trong dialog
        EditText editText1 = dialogView.findViewById(R.id.editText1);
        EditText editText2 = dialogView.findViewById(R.id.editText2);
        EditText editText3 = dialogView.findViewById(R.id.editText3);
        EditText editText4 = dialogView.findViewById(R.id.editText4);
        EditText editText5 = dialogView.findViewById(R.id.editText5);
        EditText editText6 = dialogView.findViewById(R.id.editText6);
        buttonXacNhan = dialogView.findViewById(R.id.button_xacnhan); // Nút xác nhận trong dialog

        // Thêm TextWatcher cho các EditText
        editText1.addTextChangedListener(new GenericTextWatcher(editText2));
        editText2.addTextChangedListener(new GenericTextWatcher(editText3));
        editText3.addTextChangedListener(new GenericTextWatcher(editText4));
        editText4.addTextChangedListener(new GenericTextWatcher(editText5));
        editText5.addTextChangedListener(new GenericTextWatcher(editText6));
        editText6.addTextChangedListener(new GenericTextWatcher(buttonXacNhan)); // Kết thúc nhập liệu

        // Tự động focus vào editText1 khi dialog được hiển thị
        editText1.requestFocus();

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                overlay.setVisibility(View.GONE); // Ẩn overlay nếu hủy
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // Mở bàn phím để nhập liệu vào editText1
        editText1.post(() -> {
            editText1.requestFocus();
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(editText1, InputMethodManager.SHOW_IMPLICIT);
        });

        // Disable button until all EditText are filled
        buttonXacNhan.setEnabled(false);
        buttonXacNhan.setOnClickListener(v -> {
            // Xử lý khi nút xác nhận được nhấn
            startActivity(new Intent(DatHangActivity.this, ThanhCongActivity.class)); // Chuyển đến ThanhCongActivity
            dialog.dismiss(); // Đóng dialog
            overlay.setVisibility(View.GONE); // Ẩn overlay
        });

        // Kiểm tra các EditText mỗi khi chúng thay đổi
        TextWatcher pinWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Kiểm tra nếu tất cả EditText đã được nhập đủ
                if (editText1.getText().length() == 1 &&
                        editText2.getText().length() == 1 &&
                        editText3.getText().length() == 1 &&
                        editText4.getText().length() == 1 &&
                        editText5.getText().length() == 1 &&
                        editText6.getText().length() == 1) {
                    buttonXacNhan.setEnabled(true); // Kích hoạt nút xác nhận
                } else {
                    buttonXacNhan.setEnabled(false); // Vô hiệu hóa nút xác nhận
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        editText1.addTextChangedListener(pinWatcher);
        editText2.addTextChangedListener(pinWatcher);
        editText3.addTextChangedListener(pinWatcher);
        editText4.addTextChangedListener(pinWatcher);
        editText5.addTextChangedListener(pinWatcher);
        editText6.addTextChangedListener(pinWatcher);
    }

    private class GenericTextWatcher implements TextWatcher {
        private final View nextView;

        public GenericTextWatcher(View nextView) {
            this.nextView = nextView;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Không làm gì
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 1) { // Nếu nhập một ký tự
                nextView.requestFocus(); // Chuyển đến ô tiếp theo
            }
        }
        @Override
        public void afterTextChanged(Editable s) {
            // Không làm gì
        }
    }
}
