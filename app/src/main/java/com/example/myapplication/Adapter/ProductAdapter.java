package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.ProductDetailActivity;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.util.List;
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private int viewType; // Thêm thuộc tính để xác định kiểu RecyclerView
    private Context context;

    // Constructor
    public ProductAdapter(List<Product> productList, int viewType) {
        this.productList = productList;
        this.viewType = viewType; // Khởi tạo viewType
    }

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    public ProductAdapter(Context context, List<Product> productList, int viewType) {
        this.context = context;
        this.productList = productList;
        this.viewType = viewType;
    }


    @Override
    public int getItemViewType(int position) {
        return viewType; // Trả về viewType
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        // Kiểm tra kiểu RecyclerView để inflate layout phù hợp
        if (viewType == 0) { // cardview
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);
        } else { // nền trắng tronw
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product_blank, parent, false);
        }
        return new ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textProductName.setText(product.getName());
        holder.imageProduct.setImageResource(product.getImageResource());

        // Xử lý sự kiện click vào item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product_name", product.getName());
            intent.putExtra("product_image", product.getImageResource());
            intent.putExtra("product_description", product.getDescription());
            intent.putExtra("product_price", product.getPrice());
            intent.putExtra("product_color", product.getColor());
            intent.putExtra("product_rating", product.getRating());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textProductName;
        ImageView imageProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textProductName = itemView.findViewById(R.id.product_name);
            imageProduct = itemView.findViewById(R.id.product_image);
        }
    }
}
