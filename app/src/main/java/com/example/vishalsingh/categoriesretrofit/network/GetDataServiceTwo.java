package com.example.vishalsingh.categoriesretrofit.network;

import com.example.vishalsingh.categoriesretrofit.model.Example;
import com.example.vishalsingh.categoriesretrofit.model.ExampleTwo;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface GetDataServiceTwo {
        @FormUrlEncoded
        @POST("company.php")
        Call<ExampleTwo> getCompanies(@Field("category_id") String id);
    }

