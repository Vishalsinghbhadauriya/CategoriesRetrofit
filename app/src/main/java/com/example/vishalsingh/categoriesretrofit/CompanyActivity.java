package com.example.vishalsingh.categoriesretrofit;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vishalsingh.categoriesretrofit.model.Category;
import com.example.vishalsingh.categoriesretrofit.model.Company;
import com.example.vishalsingh.categoriesretrofit.model.ExampleTwo;
import com.example.vishalsingh.categoriesretrofit.network.GetDataService;
import com.example.vishalsingh.categoriesretrofit.network.GetDataServiceTwo;
import com.example.vishalsingh.categoriesretrofit.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;


public class CompanyActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CustomAdapterTwo customAdapterTwo;
    private String id ;
    private ProgressBar progressBar;
    private TextView network;
    private Button tryagain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        network = (TextView)findViewById(R.id.network);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        tryagain = (Button)findViewById(R.id.btn_try);
        progressBar.setVisibility(View.VISIBLE);
        onDataList();

        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    progressBar.setVisibility(View.VISIBLE);
                    network.setVisibility(View.GONE);
                    tryagain.setVisibility(View.GONE);

                    onDataList();

                } else {
                    Toast.makeText(CompanyActivity.this, "Network unavailable!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    network.setVisibility(View.VISIBLE);
                    tryagain.setVisibility(View.VISIBLE);
                }
            }
        });




    }


    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
    private void onDataList() {
        if (getIntent().getExtras()!=null) {
            Intent intent = getIntent();
            id = getIntent().getStringExtra("id");
        }
        GetDataServiceTwo serviceTwo = RetrofitClientInstance.getRetrofitInstance().create(GetDataServiceTwo.class);
        retrofit2.Call<ExampleTwo> call = serviceTwo.getCompanies(id);
        call.enqueue(new Callback<ExampleTwo>() {
            @Override
            public void onResponse(retrofit2.Call<ExampleTwo> call, Response<ExampleTwo> response) {
                List<Company> companyList = response.body().getCompanies();
                progressBar.setVisibility(View.GONE);
                generateDataListTwo(companyList);

            }

            @Override
            public void onFailure(retrofit2.Call<ExampleTwo> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                network.setVisibility(View.VISIBLE);
                tryagain.setVisibility(View.VISIBLE);
                Toast.makeText(CompanyActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void generateDataListTwo(List<Company> body) {
        recyclerView = findViewById(R.id.recyclerviewTwo);
        customAdapterTwo = new CustomAdapterTwo(this,body);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CompanyActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customAdapterTwo);
    }
}
