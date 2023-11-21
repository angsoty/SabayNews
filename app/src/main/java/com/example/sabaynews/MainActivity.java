package com.example.sabaynews;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sabaynews.API.APIClient;
import com.example.sabaynews.Interface.APIInterface;
import com.example.sabaynews.adapter.CategoryAdapter;
import com.example.sabaynews.app.BaseActivity;
import com.example.sabaynews.models.HomeResponse;
import com.example.sabaynews.uis.FormArticleActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        add = findViewById(R.id.btnAdd);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recylerViewCategory);
        progressBar = findViewById(R.id.progressBar);
        getData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                getData();

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormArticleActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getData(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        progressBar.setVisibility(View.VISIBLE);

        apiInterface.getDataInHomePage().enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(@NonNull Call<HomeResponse> call, @NonNull Response<HomeResponse> response) {
                progressBar.setVisibility(View.GONE);
                assert response.body() != null;
                categoryAdapter = new CategoryAdapter(response.body().getData().getCategories(), MainActivity.this);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,1);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(categoryAdapter);
            }
            @Override
            public void onFailure(@NonNull Call<HomeResponse> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}