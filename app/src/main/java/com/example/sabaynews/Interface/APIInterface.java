package com.example.sabaynews.Interface;

import com.example.sabaynews.models.ArticlesItem;
import com.example.sabaynews.models.BaseResponse;
import com.example.sabaynews.models.HomeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {
    @GET("/StockServerApi/api/public/cms/home/data")
    Call<HomeResponse> getDataInHomePage();
    @GET("/StockServerApi/api/public/cms/article/category/{id}")
    Call<BaseResponse<List<ArticlesItem>>> getAllArticleByCategoryId(@Path("id") int id);
    @GET("/StockServerApi/api/public/cms/article/{id}")
    Call<BaseResponse<ArticlesItem>> getArticleById(@Path("id") int id);
}