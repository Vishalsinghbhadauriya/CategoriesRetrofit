package com.example.vishalsingh.categoriesretrofit.model;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("categories")
    @Expose

    private List<Category> categories = null;

    public List<Category> getCategories() {
        return categories;
    }


    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    @SerializedName("companies")
    @Expose
    private List<Company> companies = null;

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }


}