package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.example.myapplication.Manager.CartManager;
import com.example.myapplication.Model.CartItem;
import com.example.myapplication.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<CartItem> cartItems;
    private OnCartItemChangeListener cartItemChangeListener;

    public CartAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    public void setOnCartItemChangeListener(OnCartItemChangeListener listener) {
        this.cartItemChangeListener = listener;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);

        // Gán giá trị cho các view
        Picasso.get()
                .load(cartItem.getImageurl()) // URL hình ảnh lấy từ cartItem
                .into(holder.productImage); // Gán hình ảnh vào ImageView

        holder.productName.setText(cartItem.getProductName());
        holder.productSize.setText(cartItem.getSize());
        holder.productQuantity.setText(String.valueOf(cartItem.getQuantity()));
        holder.productPrice.setText(String.format("%,.0f", cartItem.getTotalPrice()) + " đ");


        // Gán trạng thái checkbox
        holder.checkbox.setChecked(cartItem.isSelected());
        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            cartItem.setSelected(isChecked);
            if (cartItemChangeListener != null) {
                cartItemChangeListener.onCartItemChanged();
            }
        });


        // Xử lý sự kiện nhấn vào nút giảm số lượng
        holder.btnDecrease.setOnClickListener(view -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.decreaseQuantity();
                holder.productQuantity.setText(String.valueOf(cartItem.getQuantity()));
                holder.productPrice.setText(String.format("%,.0f", cartItem.getTotalPrice()) + " đ");
                cartItemChangeListener.onCartItemChanged();

                CartManager cartManager = new CartManager(context);
                cartManager.decreaseQuantity(cartItem);

                notifyItemChanged(position);
            }
        });

        // Xử lý sự kiện nhấn vào nút tăng số lượng
        holder.btnIncrease.setOnClickListener(view -> {
            cartItem.increaseQuantity();
            holder.productQuantity.setText(String.valueOf(cartItem.getQuantity()));
            holder.productPrice.setText(String.format("%,.0f", cartItem.getTotalPrice()) + " đ");
            cartItemChangeListener.onCartItemChanged();

            CartManager cartManager = new CartManager(context);
            cartManager.increaseQuantity(cartItem);

            notifyItemChanged(position);
        });

        // Nút xóa: Xử lý khi người dùng click vào nút xóa
        holder.btnDelete.setOnClickListener(view -> {
            CartManager cartManager = new CartManager(context);
            cartManager.removeFromCart(cartItem);
            cartItems.remove(position);
            notifyItemRemoved(position);
        });

    }


    // Chọn tất cả các item
    public void selectAll(boolean isSelected) {
        for (CartItem item : cartItems) {
            item.setSelected(isSelected);
        }
        notifyDataSetChanged(); // Cập nhật giao diện
    }

    // Xóa các item được chọn
    public void deleteSelectedItems() {
        for (int i = cartItems.size() - 1; i >= 0; i--) {
            if (cartItems.get(i).isSelected()) {
                CartManager cartManager = new CartManager(context);
                cartManager.removeFromCart(cartItems.get(i)); // Xóa khỏi CartManager
                cartItems.remove(i); // Xóa khỏi danh sách
            }
        }
        notifyDataSetChanged();
        if (cartItemChangeListener != null) {
            cartItemChangeListener.onCartItemChanged(); // Cập nhật tổng số tiền
        }
    }













    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    // Phương thức lấy item theo vị trí
    public CartItem getItemAtPosition(int position) {
        return cartItems.get(position);
    }

    public void removeItem(int position) {
        CartItem cartItem = cartItems.get(position);

        // Xóa sản phẩm khỏi giỏ hàng trong CartManager
        CartManager cartManager = new CartManager(context);
        cartManager.removeFromCart(cartItem);

        // Xóa sản phẩm khỏi danh sách giỏ hàng
        cartItems.remove(position);
        notifyItemRemoved(position);

        // Thông báo cho listener (nếu có) để cập nhật thông tin giỏ hàng
        if (cartItemChangeListener != null) {
            cartItemChangeListener.onCartItemChanged();
        }
    }

    // Interface để thông báo sự thay đổi trong giỏ hàng
    public interface OnCartItemChangeListener {
        void onCartItemChanged();
    }

    // ViewHolder cho RecyclerView
    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productSize;
        TextView productQuantity;
        TextView productPrice;
        ImageButton btnDecrease;
        ImageButton btnIncrease;
        public ImageButton btnDelete;
        CheckBox checkbox;
        public CartViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productSize = itemView.findViewById(R.id.product_size);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            productPrice = itemView.findViewById(R.id.product_price);
            btnDecrease = itemView.findViewById(R.id.btn_decrease);
            btnIncrease = itemView.findViewById(R.id.btn_increase);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            checkbox = itemView.findViewById(R.id.product_checkbox); // Ánh xạ checkbox
        }
    }

}
