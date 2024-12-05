package com.example.myapplication.Activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.example.myapplication.Adapter.CartAdapter;
import com.example.myapplication.Manager.CartManager;
import com.example.myapplication.Model.Cart;
import com.example.myapplication.Model.CartItem;
import com.example.myapplication.R;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView cartRecyclerView;
    private TextView totalTextView;
    private TextView discountTextView;
    private TextView finalAmountTextView;
    private TextView editTextView;
    private Cart cart; // Giỏ hàng
    private CartAdapter cartAdapter; // Adapter cho RecyclerView
    private CartManager cartManager; // Quản lý giỏ hàng
    private CheckBox selectAllCheckbox;
    private Button deleteButton;
    private View editLayout; // Layout chứa CheckBox và nút Xóa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Khởi tạo CartManager
        cartManager = new CartManager(this);
        cartRecyclerView = findViewById(R.id.cart_recycler_view);

        // Lấy danh sách sản phẩm dưới dạng CartItem
        List<CartItem> cartItems = cartManager.getCartItems();
        cartAdapter = new CartAdapter(this, cartItems);

        // Cấu hình RecyclerView
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        // Khởi tạo các view
        totalTextView = findViewById(R.id.total_text_view);
        discountTextView = findViewById(R.id.total_text_view1);
        finalAmountTextView = findViewById(R.id.total_text_view2);
        selectAllCheckbox = findViewById(R.id.select_all_checkbox);
        deleteButton = findViewById(R.id.delete_selected_button);
        editLayout = findViewById(R.id.edit_layout);
        editTextView = findViewById(R.id.total_edit_text_view);


        // Tạo giỏ hàng
        cart = new Cart();
        for (CartItem item : cartItems) {
            cart.addItem(item); // Thêm từng mục vào giỏ hàng
        }

        // Cập nhật tổng tiền, giảm giá và thành tiền
        updateTotalAmount();

        // Đăng ký lắng nghe sự thay đổi trong adapter (khi có thay đổi số lượng sản phẩm)
        cartAdapter.setOnCartItemChangeListener(() -> {
            updateCartData(); // Cập nhật lại giỏ hàng và tổng tiền khi có thay đổi
        });
        // Lắng nghe sự kiện click vào "Sửa"
        editTextView.setOnClickListener(v -> toggleEditLayoutVisibility());

        // Xử lý "Chọn tất cả"
        selectAllCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            cartAdapter.selectAll(isChecked);
        });

        // Xử lý "Xóa mục đã chọn"
        deleteButton.setOnClickListener(v -> {
            cartAdapter.deleteSelectedItems();
            updateCartData();
            Toast.makeText(this, "Đã xóa các mục đã chọn!", Toast.LENGTH_SHORT).show();
        });
    }


    // Hiển thị hoặc ẩn layout chỉnh sửa
    private void toggleEditLayoutVisibility() {
        if (editLayout.getVisibility() == View.GONE) {
            editLayout.setVisibility(View.VISIBLE);
            editTextView.setText("Hoàn tất"); // Đổi chữ "Sửa" thành "Hoàn tất"
        } else {
            editLayout.setVisibility(View.GONE);
            editTextView.setText("Sửa"); // Đổi lại thành "Sửa"
        }
    }



    private void updateTotalAmount() {
        double totalAmount = cart.getTotalAmount();
        double discountAmount = cart.getDiscountAmount();
        double finalAmount = cart.getTotalAfterDiscount();
        int totalItems = cart.getTotalItems(); // Lấy tổng số sản phẩm từ giỏ hàng

        // Cập nhật TextView với đơn vị là VND
        totalTextView.setText("Tổng cộng: " + String.format("%,.0f", totalAmount) + " VND");
        discountTextView.setText("Giảm giá: " + String.format("%,.0f", discountAmount) + " VND");
        finalAmountTextView.setText("Thành tiền: " + String.format("%,.0f", finalAmount) + " VND");

        // Cập nhật số lượng sản phẩm
        TextView totalItemsTextView = findViewById(R.id.item_count_text_view);
        totalItemsTextView.setText("(" + totalItems + ")"); // Hiển thị tổng số sản phẩm
    }


    // Cập nhật dữ liệu giỏ hàng và tính lại tổng số tiền
    private void updateCartData() {
        // Lấy danh sách sản phẩm hiện tại từ CartManager
        List<CartItem> updatedCartItems = cartManager.getCartItems();
        cart.clear();
        for (CartItem item : updatedCartItems) {
            cart.addItem(item);
        }
        updateTotalAmount();
    }
}