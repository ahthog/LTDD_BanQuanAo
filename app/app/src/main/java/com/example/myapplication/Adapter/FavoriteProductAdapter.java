package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.ProductDetailActivity;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteProductAdapter extends RecyclerView.Adapter<FavoriteProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> favoriteProducts;  // Thay đổi từ mảng thành List
    private final int viewType;

    // Constructor để khởi tạo adapter với dữ liệu
    public FavoriteProductAdapter(Context context, List<Product> favoriteProducts,  int viewType) {
        this.context = context;
        this.favoriteProducts = favoriteProducts;
        this.viewType = viewType;
    }
    @Override
    public int getItemViewType(int position) {
        return viewType; // Trả về viewType, có thể phân biệt loại hiển thị
    }
    // Phương thức này được gọi khi RecyclerView tạo ViewHolder mới
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite_product, parent, false);
        return new ViewHolder(view);
    }

    // Phương thức này được gọi khi RecyclerView cần cập nhật ViewHolder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = favoriteProducts.get(position);
        // Cập nhật các trường TextView
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(String.format("%.2f", product.getPrice()) + " đ");
        holder.colorTextView.setText("Màu sắc: " + product.getColor());

        // Kiểm tra xem có nhận được dữ liệu đúng không
        Log.d("ProductInfo", "Name: " + product.getName() + ", Price: " + product.getPrice() +
                ", Color: " + product.getColor() + ", Image URL: " + product.getImageUrl());

        // Cập nhật ViewHolder với thông tin sản phẩm
        holder.bindProduct(product);

        // Dùng Picasso để tải hình ảnh từ URL lấy từ cơ sở dữ liệu
        String hinhAnhUrl = "http://192.168.21.155:8081/appadroidbanquanao/images/" + product.getImageUrl();  // Đây là URL của hình ảnh
        // Log URL để kiểm tra
        Log.d("ImageURL", hinhAnhUrl);

        // Đảm bảo tải lại từ mạng và không sử dụng bộ nhớ cache
        Picasso.get()
                .load(hinhAnhUrl)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(holder.imageView);

        // Xử lý sự kiện click vào item để chuyển sang màn hình chi tiết sản phẩm
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            // Truyền dữ liệu sản phẩm vào Intent
            intent.putExtra("product_name", product.getName());
            intent.putExtra("product_image", product.getImageUrl());  // Truyền imageUrl thay vì imageResource
            intent.putExtra("product_description", product.getDescription());
            intent.putExtra("product_price", product.getPrice());
            intent.putExtra("product_color", product.getColor());
            intent.putExtra("product_rating", product.getRating());
            context.startActivity(intent);  // Bắt đầu activity mới
        });

        // Cập nhật Spinner cho kích thước sản phẩm (nếu cần thiết)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.product_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.sizeSpinner.setAdapter(adapter);
    }


    @Override
    public int getItemCount() {
        return favoriteProducts.size();  // Trả về số lượng sản phẩm trong danh sách
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView, priceTextView, colorTextView;
        Spinner sizeSpinner;
        Button addToCartButton;

        private Product currentProduct; // Biến để lưu sản phẩm hiện tại

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

        // Phương thức bind sản phẩm vào ViewHolder
        public void bindProduct(Product product) {
            this.currentProduct = product;
        }
    }

    // Phương thức cập nhật danh sách sản phẩm yêu thích
    public void updateFavoriteProducts(List<Product> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
        notifyDataSetChanged();  // Thông báo adapter thay đổi dữ liệu
    }
}
