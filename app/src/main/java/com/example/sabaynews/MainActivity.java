package com.example.sabaynews;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.sabaynews.API.APIClient;
import com.example.sabaynews.Interface.APIInterface;
import com.example.sabaynews.adapter.CategoryAdapter;
import com.example.sabaynews.app.BaseActivity;
import com.example.sabaynews.data.local.UserSharePreference;
import com.example.sabaynews.models.BaseResponse;
import com.example.sabaynews.models.Category;
import com.example.sabaynews.models.HomeResponse;
import com.example.sabaynews.uis.FormArticleActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        apiInterface.getAllCategory("Bearer"+UserSharePreference.getAccessToken(this)).enqueue(new Callback<BaseResponse<List<Category>>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<List<Category>>> call, @NonNull Response<BaseResponse<List<Category>>> response) {
                if (response.isSuccessful())
                {
                    assert response.body() != null;
                    showMessage(response.body().getData().toString());
                }
                else
                {
                    showMessage("No Authentication");
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<List<Category>>> call, @NonNull Throwable t) {
                showMessage("No Authentication"+t.getLocalizedMessage());
            }
        });
    }
    private void initView(){
        ImageView logout = findViewById(R.id.ivlogout);
        FloatingActionButton add = findViewById(R.id.btnAdd);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recylerViewCategory);
        progressBar = findViewById(R.id.progressBar);
        getData();
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
            getData();

        });
        add.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FormArticleActivity.class);
            startActivity(intent);
        });
        logout.setOnClickListener(v->{
            UserSharePreference.removeToken(MainActivity.this);
            onResume();

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