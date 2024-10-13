package com.example.testapplication.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;
import com.example.testapplication.Fragment_menubar.Product;

public class ProductNewAdapter extends RecyclerView.Adapter<ProductNewAdapter.ViewHolder> {
    private final Context context;
    private final Product[] products;
    private OnItemClickListener listener;

    public ProductNewAdapter(Context context, Product[] products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products[position];
        holder.imageView.setImageResource(product.getImageResource()); // Set ảnh cho item

        // Xử lý sự kiện click vào sản phẩm
        holder.imageView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(product); // Gọi sự kiện click và truyền sản phẩm
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }
}