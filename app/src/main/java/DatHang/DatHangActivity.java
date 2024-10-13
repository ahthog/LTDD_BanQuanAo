package DatHang;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testapplication.R;

public class DatHangActivity extends AppCompatActivity {

    private Button confirmButton;
    private View overlay;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Đảm bảo bạn đang đặt layout chính

        confirmButton = findViewById(R.id.button2); // Nút xác nhận
        overlay = findViewById(R.id.overlay); // View overlay

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Làm mờ layout chính
                overlay.setVisibility(View.VISIBLE);

                // Hiển thị dialog với thông báo nhập mã PIN
                showPinDialog();
            }
        });
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

        // Thêm TextWatcher cho các EditText
        editText1.addTextChangedListener(new GenericTextWatcher(editText2));
        editText2.addTextChangedListener(new GenericTextWatcher(editText3));
        editText3.addTextChangedListener(new GenericTextWatcher(editText4));
        editText4.addTextChangedListener(new GenericTextWatcher(editText5));
        editText5.addTextChangedListener(new GenericTextWatcher(editText6));

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                overlay.setVisibility(View.GONE); // Ẩn overlay nếu hủy
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
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
        }
    }

}
