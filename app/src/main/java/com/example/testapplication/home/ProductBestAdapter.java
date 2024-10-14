package com.example.testapplication.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapplication.DetailFragment.ProductDetailFragment;
import com.example.testapplication.Fragment_menubar.Product;
import com.example.testapplication.R;

public class ProductBestAdapter extends RecyclerView.Adapter<ProductBestAdapter.ViewHolder> {
    private final Context context;
    private final Product[] bestSellingProducts;
    private OnItemClickListener listener;

    public ProductBestAdapter(Context context, Product[] bestSellingProducts) {
        this.context = context;
        this.bestSellingProducts = bestSellingProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_best, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = bestSellingProducts[position];
        holder.imageView.setImageResource(product.getImageResource()); // Set hình ảnh sản phẩm

        // Đặt sự kiện click cho ImageView
        holder.imageView.setOnClickListener(v -> {
            // Log để kiểm tra nếu click đã được nhận
            Log.d("ProductBestAdapter", "Image clicked");

            // Kiểm tra nếu listener không phải null
            if (listener != null) {
                listener.onItemClick(product);  // Gọi phương thức onItemClick trong listener
            }
        });
    }

    @Override
    public int getItemCount() {
        return bestSellingProducts.length;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image_best); // Thay đổi id nếu cần
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }
}