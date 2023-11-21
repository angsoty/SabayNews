package com.example.sabaynews.uis;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sabaynews.API.APIClient;
import com.example.sabaynews.Interface.APIInterface;
import com.example.sabaynews.R;
import com.example.sabaynews.adapter.CategoryBaseAdapter;
import com.example.sabaynews.app.BaseActivity;
import com.example.sabaynews.models.ArticlesItem;
import com.example.sabaynews.models.BaseResponse;
import com.example.sabaynews.models.CategoriesItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormArticleActivity extends BaseActivity {
    private  CategoryBaseAdapter categoryBaseAdapter;
    private Spinner spinnerCategory;
    private APIInterface apiInterface;
    private TextView appBarTitle,create;
    private ProgressBar progressBar;
    private CategoriesItem categoriesItem;
    private EditText titleArt,desc;
    List<CategoriesItem> categoriesItemList = new ArrayList<>();
    ArticlesItem articlesItem = new ArticlesItem();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_article);
        initView();
        getAllCategory();


        create.setOnClickListener(v -> {
            if(titleArt.getText().toString().equals(""))
            {
                Toast.makeText(FormArticleActivity.this,"Please enterr title",Toast.LENGTH_LONG).show();
                return;
            }
            if(desc.getText().toString().equals(""))
            {
                Toast.makeText(FormArticleActivity.this,"Please enterr DESC",Toast.LENGTH_LONG).show();
                return;
            }
            createAndUpdate();
        });

    }
    public  void createAndUpdate(){

        articlesItem.setCategory(categoriesItem);
        articlesItem.setTitle(titleArt.getText().toString().trim());
        articlesItem.setDescription(desc.getText().toString().trim());
        articlesItem.setStatus("ACT");
        if (articlesItem.getId()!=0)
        {
            apiInterface.updateArticle(articlesItem).enqueue(new Callback<BaseResponse<String>>() {
                @Override
                public void onResponse(@NonNull Call<BaseResponse<String>> call, @NonNull Response<BaseResponse<String>> response) {
                    onBackPressed();
                }

                @Override
                public void onFailure(@NonNull Call<BaseResponse<String>> call, @NonNull Throwable t) {
                    Toast.makeText(FormArticleActivity.this," "+ t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }else {
            apiInterface.createArticle(articlesItem).enqueue(new Callback<BaseResponse<String>>() {
                @Override
                public void onResponse(@NonNull Call<BaseResponse<String>> call, @NonNull Response<BaseResponse<String>> response) {
                    onBackPressed();
                }

                @Override
                public void onFailure(@NonNull Call<BaseResponse<String>> call, @NonNull Throwable t) {
                    Toast.makeText(FormArticleActivity.this," "+ t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    @SuppressLint("SetTextI18n")
    public void initView(){
        titleArt =findViewById(R.id.editTitle);
        desc =findViewById(R.id.txtDesc);
        categoriesItem = new CategoriesItem();
        create = findViewById(R.id.btnAdd);
        progressBar = findViewById(R.id.progressBar);
        appBarTitle=findViewById(R.id.title);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        appBarTitle.setText("Create Article");
    }
    public void getAllCategory(){
        progressBar.setVisibility(View.VISIBLE);
        apiInterface.getAllCategories().enqueue(new Callback<BaseResponse<List<CategoriesItem>>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<List<CategoriesItem>>> call, @NonNull Response<BaseResponse<List<CategoriesItem>>> response) {
                assert response.body() != null;
                categoriesItem= response.body().getData().get(0);
                categoriesItemList =response.body().getData();
                categoryBaseAdapter = new CategoryBaseAdapter(response.body().getData(),FormArticleActivity.this);
                spinnerCategory.setAdapter(categoryBaseAdapter);
                progressBar.setVisibility(View.GONE);
                spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        categoriesItem = response.body().getData().get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                getArticleById();
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<List<CategoriesItem>>> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public void getArticleById(){
        Intent intent =getIntent();
        int id=intent.getIntExtra("ID",0);
        if (id!=0)
        {
            articlesItem.setId(id);

            appBarTitle.setText("Update Article");
            create.setText("Update");
            apiInterface.getArticleById(id).enqueue(new Callback<BaseResponse<ArticlesItem>>() {
                @Override
                public void onResponse(@NonNull Call<BaseResponse<ArticlesItem>> call, @NonNull Response<BaseResponse<ArticlesItem>> response) {
                    assert response.body() != null;
                    titleArt.setText(response.body().getData().getTitle());
                    desc.setText(response.body().getData().getDescription());
                    articlesItem.setImageUrl(response.body().getData().getImageUrl());
                    for (int i=0;i<categoriesItemList.size();i++){
                        if (categoriesItemList.get(i).getId()==response.body().getData().getCategory().getId()){
                            spinnerCategory.setSelection(i);
                        }
                    }

                }

                @Override
                public void onFailure(@NonNull Call<BaseResponse<ArticlesItem>> call, @NonNull Throwable t) {

                }
            });
        }
    }
}