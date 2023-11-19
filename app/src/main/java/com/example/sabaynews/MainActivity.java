package com.example.sabaynews;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sabaynews.API.APIClient;
import com.example.sabaynews.Interface.APIInterface;
import com.example.sabaynews.adapter.CategoryAdapter;
import com.example.sabaynews.app.BaseActivity;
import com.example.sabaynews.models.HomeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private APIInterface apiInterface;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        recyclerView = findViewById(R.id.recylerViewCategory);
        progressBar = findViewById(R.id.progressBar);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        progressBar.setVisibility(View.VISIBLE);
        apiInterface.getDataInHomePage().enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
//                Toast.makeText(MainActivity.this," "+response.body().getData().getCategories().toString(),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                categoryAdapter = new CategoryAdapter(response.body().getData().getCategories(), MainActivity.this);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,1);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(categoryAdapter);
            }
            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}