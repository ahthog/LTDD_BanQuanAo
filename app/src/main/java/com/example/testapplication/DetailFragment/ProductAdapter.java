package com.example.testapplication.DetailFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.Fragment_menubar.Product;
import com.example.testapplication.R;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final Context context;
    private final List<Product> products;  // Thay đổi từ Product[] sang List<Product>

    // Constructor nhận List<Product>
    public ProductAdapter(Context context, List<Product> products) {
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
        Product product = products.get(position);  // Truy cập theo vị trí trong List
        holder.imageView.setImageResource(product.getImageResource());  // Gán ảnh sản phẩm
        holder.textView.setText(product.getName());  // Gán tên sản phẩm
    }

    @Override
    public int getItemCount() {
        return products.size();  // Lấy kích thước của List
    }

    // ViewHolder để quản lý các thành phần của mỗi item sản phẩm
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
            textView = itemView.findViewById(R.id.product_name);
        }
    }
}