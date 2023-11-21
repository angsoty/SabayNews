package com.example.sabaynews.uis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sabaynews.API.APIClient;
import com.example.sabaynews.Interface.APIInterface;
import com.example.sabaynews.R;
import com.example.sabaynews.adapter.ArticleAdapter;
import com.example.sabaynews.models.ArticlesItem;
import com.example.sabaynews.models.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListArticleByCategoryIdActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_article_by_category_id);
        TextView title = findViewById(R.id.title);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycleViewArticle);
        ImageView arrowBack = findViewById(R.id.ivBack);
        arrowBack.setOnClickListener(v -> onBackPressed());
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Intent intent = getIntent();
        int id = intent.getIntExtra("ID",0);
        String name = intent.getStringExtra("NAME");
        title.setText(name);
        progressBar.setVisibility(View.VISIBLE);
        try {
            apiInterface.getAllArticleByCategoryId(id).enqueue(new Callback<BaseResponse<List<ArticlesItem>>>() {
                @Override
                public void onResponse(@NonNull Call<BaseResponse<List<ArticlesItem>>> call, @NonNull Response<BaseResponse<List<ArticlesItem>>> response) {
                    progressBar.setVisibility(View.GONE);
                    assert response.body() != null;
                    articleAdapter= new ArticleAdapter(response.body().getData(),ListArticleByCategoryIdActivity.this,"LIST");
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ListArticleByCategoryIdActivity.this,1);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(articleAdapter);
                }

                @Override
                public void onFailure(@NonNull Call<BaseResponse<List<ArticlesItem>>> call, @NonNull Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }catch (Exception ex)
        {
            progressBar.setVisibility(View.GONE);
        }
    }
}