package com.example.testapplication.DetailFragment;

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
    private List<Product> productList;

    // Constructor
    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout cho item sản phẩm
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Lấy sản phẩm tại vị trí hiện tại
        Product product = productList.get(position);

        // Thiết lập tên và giá sản phẩm
        holder.textViewProductName.setText(product.getName());
        holder.textViewProductPrice.setText(String.format("%,d đ", product.getPrice())); // Định dạng giá

        // Thiết lập hình ảnh sản phẩm
        holder.imageViewProduct.setImageResource(product.getImageResource());
    }

    @Override
    public int getItemCount() {
        // Trả về số lượng sản phẩm
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewProductName;
        public TextView textViewProductPrice;
        public ImageView imageViewProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            // Khởi tạo các thành phần trong layout
            textViewProductName = itemView.findViewById(R.id.product_name);
            imageViewProduct = itemView.findViewById(R.id.product_image_new);
        }


    }


}
