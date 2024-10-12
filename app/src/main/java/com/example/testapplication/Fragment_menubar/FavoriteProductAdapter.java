package com.example.testapplication.Fragment_menubar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;

import java.util.List;

public class FavoriteProductAdapter extends RecyclerView.Adapter<FavoriteProductAdapter.ViewHolder> {

    private Context context;
    private Product[] favoriteProducts;

    public FavoriteProductAdapter(Context context, Product[] favoriteProducts) {
        this.context = context;
        this.favoriteProducts = favoriteProducts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = favoriteProducts[position];

        // Cập nhật hình ảnh sản phẩm
        holder.imageView.setImageResource(product.getImageResource());

        // Cập nhật tên sản phẩm
        holder.nameTextView.setText(product.getName());

        // Cập nhật giá sản phẩm
        holder.priceTextView.setText(product.getPrice());

        // Cập nhật màu sắc sản phẩm
        holder.colorTextView.setText("Màu sắc: " + product.getColor());

        // Chỉnh sửa đánh giá sản phẩm (nếu có)
        // Có thể bạn sẽ cần đánh giá cho việc hiển thị xếp hạng của sản phẩm.
        // Ví dụ: holder.ratingTextView.setText("Đánh giá: " + product.getRating());

        // Xử lý kích cỡ sản phẩm
        // Ví dụ sử dụng Spinner cho kích cỡ
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.product_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.sizeSpinner.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return favoriteProducts.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView, priceTextView, colorTextView;
        Spinner sizeSpinner;
        Button addToCartButton;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_product);
            nameTextView = itemView.findViewById(R.id.tv_product_name);
            priceTextView = itemView.findViewById(R.id.tv_product_price);
            colorTextView = itemView.findViewById(R.id.tv_product_color);
            sizeSpinner = itemView.findViewById(R.id.spinner_product_size);
            addToCartButton = itemView.findViewById(R.id.btn_add_to_cart);

            // Thêm sự kiện click cho nút "Thêm vào giỏ hàng"
            addToCartButton.setOnClickListener(v -> {
                // Xử lý hành động thêm vào giỏ hàng
                // Có thể thêm logic thêm sản phẩm vào giỏ hàng của người dùng
            });
        }
    }
}
