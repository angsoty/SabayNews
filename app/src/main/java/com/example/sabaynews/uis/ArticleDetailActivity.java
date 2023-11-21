package com.example.sabaynews.uis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sabaynews.API.APIClient;
import com.example.sabaynews.Interface.APIInterface;
import com.example.sabaynews.R;
import com.example.sabaynews.models.ArticlesItem;
import com.example.sabaynews.models.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDetailActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView articleTitle;
    private TextView desc;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        TextView title = findViewById(R.id.title);
        articleTitle=findViewById(R.id.txtArticleTitle);
        desc=findViewById(R.id.txtArticleDesc);
        imageView= findViewById(R.id.imgViewArticle);
        progressBar=findViewById(R.id.progressBar);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        title.setText("ពត័មានលំអិត");
        ImageView arrowBack = findViewById(R.id.ivBack);
        arrowBack.setOnClickListener(v -> onBackPressed());
        Intent intent = getIntent();
        int id = intent.getIntExtra("ID",0);
        progressBar.setVisibility(View.VISIBLE);
        try{
            apiInterface.getArticleById(id).enqueue(new Callback<BaseResponse<ArticlesItem>>() {
                @Override
                public void onResponse(@NonNull Call<BaseResponse<ArticlesItem>> call, @NonNull Response<BaseResponse<ArticlesItem>> response) {
                    progressBar.setVisibility(View.GONE);
                    assert response.body() != null;
                    articleTitle.setText(response.body().getData().getTitle());
                    desc.setText(response.body().getData().getDescription());
                    Glide.with(ArticleDetailActivity.this   )
                            .load(response.body().getData().getImageUrl())
                            .centerCrop()
                            .placeholder(R.drawable.image)
                            .into(imageView);

                }

                @Override
                public void onFailure(@NonNull Call<BaseResponse<ArticlesItem>> call, @NonNull Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }catch (Exception ex)
        {
            progressBar.setVisibility(View.GONE);
        }


    }
}