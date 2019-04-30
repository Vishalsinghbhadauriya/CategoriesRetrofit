package com.example.vishalsingh.categoriesretrofit;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vishalsingh.categoriesretrofit.model.Category;
import com.example.vishalsingh.categoriesretrofit.model.Company;
import com.example.vishalsingh.categoriesretrofit.model.Example;
import com.example.vishalsingh.categoriesretrofit.network.GetDataService;
import com.example.vishalsingh.categoriesretrofit.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView network;
    private Activity view;
    private Button tryagain;
    private List<Category> categoryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        network = (TextView)findViewById(R.id.network);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        tryagain = (Button)findViewById(R.id.btn_try);
        progressBar.setVisibility(View.VISIBLE);

        onDataList();


     //   progressDoalog = new ProgressDialog(MainActivity.this);
     //   progressDoalog.setMessage("Loading....");
     //   progressDoalog.show();

        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    progressBar.setVisibility(View.VISIBLE);
                    network.setVisibility(View.GONE);
                    tryagain.setVisibility(View.GONE);

                    onDataList();

                } else {
                    Toast.makeText(MainActivity.this, "Network unavailable!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    network.setVisibility(View.VISIBLE);
                    tryagain.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void generateDataList(List<Category> body) {
        recyclerView = findViewById(R.id.recyclerviewone);
        adapter = new CustomAdapter(this, body);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this
                , new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

          //      Toast.makeText(MainActivity.this, categoryList.get(position).getId() + "", Toast.LENGTH_SHORT).show();

                final Intent intent;
                intent = new Intent(MainActivity.this,CompanyActivity.class);
                intent.putExtra("id",categoryList.get(position).getId());
                startActivity(intent);

                }
        }));
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

        public void onDataList(){
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<Example> call = service.getCategories();

            call.enqueue(new Callback<Example>(){

                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    // progressDoalog.dismiss();
                    progressBar.setVisibility(View.GONE);
                    categoryList=response.body().getCategories();

                    generateDataList(categoryList);


                    }
                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    // progressDoalog.dismiss();
                    progressBar.setVisibility(View.GONE);
                    network.setVisibility(View.VISIBLE);
                    tryagain.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        }



}
