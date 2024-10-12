package com.example.testapplication.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapplication.DetailFragment.ProductDetailFragment;
import com.example.testapplication.Fragment_menubar.Product;
import com.example.testapplication.R;

public class HomeProductBestAdapter extends RecyclerView.Adapter<HomeProductBestAdapter.ViewHolder> {
    private final Context context;
    private final Product[] bestSellingProducts;
    private OnItemClickListener listener;

    public HomeProductBestAdapter(Context context, Product[] bestSellingProducts) {
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

        holder.imageView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(product); // Truyền sản phẩm khi click vào item
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
            imageView = itemView.findViewById(R.id.product_image); // Thay đổi id nếu cần
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }
}
