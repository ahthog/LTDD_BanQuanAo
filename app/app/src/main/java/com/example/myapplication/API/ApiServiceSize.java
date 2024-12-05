package com.example.myapplication.API;
import com.example.myapplication.Model.Size;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceSize {
    @GET("getSize")  // URL của API
    Call<List<Size>> getSizeList();


}
