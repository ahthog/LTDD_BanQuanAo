package com.example.testapplication.DetailFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;

import com.example.testapplication.Fragment_menubar.Product;
import com.example.testapplication.R;

public class ProductDetailFragment extends Fragment {

    private ImageView productImage;
    private TextView productName, productColor, productDescription, productPrice;
    private RatingBar ratingBar;
    private Button addToCartBtn;
    private Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout cho fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        // Khởi tạo các view
        productImage = view.findViewById(R.id.productImage);
        productName = view.findViewById(R.id.productName);
        productColor = view.findViewById(R.id.productColor);
        productDescription = view.findViewById(R.id.productDescription);
        productPrice = view.findViewById(R.id.productPrice);
        ratingBar = view.findViewById(R.id.ratingBar);


        // Lấy dữ liệu từ Bundle
        if (getArguments() != null) {
            product = (Product) getArguments().getSerializable("product");  // Lấy đối tượng Product từ Bundle
        }

        // Kiểm tra nếu đối tượng Product không null và gán giá trị cho các view
        if (product != null) {
            // Ghi log để kiểm tra xem sản phẩm có được nhận đúng không
            Log.d("ProductDetailFragment", "Received product: " + product.getName());

            productImage.setImageResource(product.getImageResource());
            productName.setText(product.getName());
            productColor.setText("Màu: " + product.getColor());
            productDescription.setText(product.getDescription());
            productPrice.setText(product.getPrice());
            ratingBar.setRating(product.getRating());
        }

        return view;
    }
}