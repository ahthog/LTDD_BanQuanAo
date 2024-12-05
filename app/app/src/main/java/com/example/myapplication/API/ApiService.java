package com.example.myapplication.API;

import com.example.myapplication.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api.php") // Lấy sản phẩm theo categoryId
    Call<List<Product>> getProductsByCategory(@Query("categoryId") int categoryId);
    @GET("api.php") // Đường dẫn API của bạn
    Call<List<Product>> getAllProducts();
}
