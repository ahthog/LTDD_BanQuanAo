package com.example.testapplication.DetailFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import com.example.testapplication.R;

public class ProductDetailFragment extends Fragment {

    private ImageView productImage;
    private TextView productName, productColor, productDescription, productPrice, sizeSelected, quantityText;
    private RatingBar ratingBar;
    private ImageButton addToCartBtn;
    private Button decrementBtn, incrementBtn;
    private int quantity = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        // Khởi tạo các view
        productImage = view.findViewById(R.id.productImage);
        productName = view.findViewById(R.id.productName);
        productColor = view.findViewById(R.id.productColor);
        productDescription = view.findViewById(R.id.productDescription);
        productPrice = view.findViewById(R.id.productPrice);
        ratingBar = view.findViewById(R.id.ratingBar);
        addToCartBtn = view.findViewById(R.id.addToCartBtn);
        sizeSelected = view.findViewById(R.id.sizeSelected);
        quantityText = view.findViewById(R.id.quantityText);
        decrementBtn = view.findViewById(R.id.decrementBtn);
        incrementBtn = view.findViewById(R.id.incrementBtn);

        // Lấy dữ liệu từ Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            int productImageRes = bundle.getInt("productImage");
            String name = bundle.getString("productName");
            String color = bundle.getString("productColor");
            String description = bundle.getString("productDescription");
            String price = bundle.getString("productPrice");
            float rating = bundle.getFloat("productRating");

            // Gán giá trị vào các view
            productImage.setImageResource(productImageRes);
            productName.setText(name);
            productColor.setText("Màu sắc: " + color);
            productDescription.setText(description);
            productPrice.setText(price);
            ratingBar.setRating(rating);
        }

        // Xử lý sự kiện tăng giảm số lượng
        decrementBtn.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                quantityText.setText(String.valueOf(quantity));
            }
        });

        incrementBtn.setOnClickListener(v -> {
            quantity++;
            quantityText.setText(String.valueOf(quantity));
        });

        // Xử lý sự kiện thêm vào giỏ hàng
        addToCartBtn.setOnClickListener(v -> {
            // Logic thêm sản phẩm vào giỏ hàng
            // Đây là nơi bạn có thể thêm sản phẩm vào giỏ hàng (thực hiện logic giỏ hàng của bạn tại đây)
        });

        return view;
    }
}
