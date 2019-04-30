package com.example.vishalsingh.categoriesretrofit.network;

import com.example.vishalsingh.categoriesretrofit.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("categories.php")
    Call<Example> getCategories();
}
