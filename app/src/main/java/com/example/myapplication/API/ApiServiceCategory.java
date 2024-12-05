package com.example.myapplication.API;
import com.example.myapplication.Model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceCategory {
    @GET("category.php")  // URL của API trên server
    Call<List<Category>> getCategories();
}
