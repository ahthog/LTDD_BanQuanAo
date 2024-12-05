package com.example.myapplication.API;

import com.example.myapplication.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceFavProduct {
    @GET("getSanPhamYeuThich.php") // Đường dẫn tới API của bạn
    Call<List<Product>> getAllFavProducts(@Query("userId") int userId);  // Truyền userId vào API để lấy sản phẩm yêu thích
}
