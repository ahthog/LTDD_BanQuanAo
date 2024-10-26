package DatHang;

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
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.testapplication.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.DatHangAdapter;
import GioHang.GioHangActivity;

public class DatHangActivity extends AppCompatActivity {

    private Button confirmButton;
    private Button buttonXacNhan; // Nút xác nhận trong dialog
    private View overlay;
    private RecyclerView recyclerView;
    private DatHangAdapter adapter;
    private List<DatHang> datHangList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang);

        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.rcv_dathang);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo dữ liệu cho danh sách đặt hàng
        datHangList = new ArrayList<>();
        datHangList.add(new DatHang("Sản phẩm 1", "M", 1, 100000,  R.drawable.mathang1));
        datHangList.add(new DatHang("Sản phẩm 2", "L", 2, 200000,  R.drawable.mathang2));
        datHangList.add(new DatHang("Sản phẩm 3", "XL", 3, 300000, R.drawable.mathang3));

        // Khởi tạo Adapter và gán cho RecyclerView
        adapter = new DatHangAdapter(this, datHangList);
        recyclerView.setAdapter(adapter);

        // Khởi tạo các thành phần giao diện
        confirmButton = findViewById(R.id.button2);
        overlay = findViewById(R.id.overlay);
        ImageView imageBack = findViewById(R.id.imageback);
        TextView nhapDiaChi = findViewById(R.id.nhapdiachi1);

        // Xử lý sự kiện nhấn nút quay lại
        imageBack.setOnClickListener(v -> {
            startActivity(new Intent(DatHangActivity.this, GioHangActivity.class));
            finish();
        });

        // Xử lý sự kiện nhấn vào TextView nhập địa chỉ
        nhapDiaChi.setOnClickListener(v -> {
            showAddressInputDialog();
        });

        confirmButton.setOnClickListener(v -> {
            overlay.setVisibility(View.VISIBLE);
            showPinDialog();
        });
    }

    private void showAddressInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_nhap_dia_chi, null);

        builder.setView(dialogView);
        EditText editTextDiaChi = dialogView.findViewById(R.id.editTextDiaChi);
        builder.setPositiveButton("Xác nhận", (dialog, which) -> {
            String diaChi = editTextDiaChi.getText().toString().trim();
            if (!diaChi.isEmpty()) {
                TextView nhapDiaChi = findViewById(R.id.nhapdiachi1);
                nhapDiaChi.setText(diaChi);
                Toast.makeText(DatHangActivity.this, "Địa chỉ: " + diaChi, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DatHangActivity.this, "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showPinDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_thong_bao, null);

        builder.setView(dialogView);
        EditText editText1 = dialogView.findViewById(R.id.editText1);
        EditText editText2 = dialogView.findViewById(R.id.editText2);
        EditText editText3 = dialogView.findViewById(R.id.editText3);
        EditText editText4 = dialogView.findViewById(R.id.editText4);
        EditText editText5 = dialogView.findViewById(R.id.editText5);
        EditText editText6 = dialogView.findViewById(R.id.editText6);
        buttonXacNhan = dialogView.findViewById(R.id.button_xacnhan);

        editText1.addTextChangedListener(new GenericTextWatcher(editText2));
        editText2.addTextChangedListener(new GenericTextWatcher(editText3));
        editText3.addTextChangedListener(new GenericTextWatcher(editText4));
        editText4.addTextChangedListener(new GenericTextWatcher(editText5));
        editText5.addTextChangedListener(new GenericTextWatcher(editText6));
        editText6.addTextChangedListener(new GenericTextWatcher(buttonXacNhan));

        editText1.requestFocus();
        builder.setNegativeButton("Hủy", (dialog, which) -> {
            dialog.dismiss();
            overlay.setVisibility(View.GONE);
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        editText1.post(() -> {
            editText1.requestFocus();
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(editText1, InputMethodManager.SHOW_IMPLICIT);
        });

        buttonXacNhan.setEnabled(false);
        buttonXacNhan.setOnClickListener(v -> {
            startActivity(new Intent(DatHangActivity.this, ThanhCongActivity.class));
            dialog.dismiss();
            overlay.setVisibility(View.GONE);
        });

        TextWatcher pinWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText1.getText().length() == 1 &&
                        editText2.getText().length() == 1 &&
                        editText3.getText().length() == 1 &&
                        editText4.getText().length() == 1 &&
                        editText5.getText().length() == 1 &&
                        editText6.getText().length() == 1) {
                    buttonXacNhan.setEnabled(true);
                } else {
                    buttonXacNhan.setEnabled(false);
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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 1) {
                nextView.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    }
}
