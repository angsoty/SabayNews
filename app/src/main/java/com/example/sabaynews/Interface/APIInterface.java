package com.example.sabaynews.Interface;

import com.example.sabaynews.models.ArticlesItem;
import com.example.sabaynews.models.BaseResponse;
import com.example.sabaynews.models.CategoriesItem;
import com.example.sabaynews.models.HomeResponse;
import com.example.sabaynews.models.LoginRequest;
import com.example.sabaynews.models.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    @GET("/StockServerApi/api/public/cms/home/data")
    Call<HomeResponse> getDataInHomePage();
    @GET("/StockServerApi/api/public/cms/article/category/{id}")
    Call<BaseResponse<List<ArticlesItem>>> getAllArticleByCategoryId(@Path("id") int id);
    @GET("/StockServerApi/api/public/cms/article/{id}")
    Call<BaseResponse<ArticlesItem>> getArticleById(@Path("id") int id);
    @GET("StockServerApi/api/public/cms/category/list")
    Call<BaseResponse<List<CategoriesItem>>> getAllCategories();
    @POST("/StockServerApi/api/public/cms/article/create")
    Call<BaseResponse<String>> createArticle(@Body ArticlesItem req);
    @POST("/StockServerApi/api/public/cms/article/update")
    Call<BaseResponse<String>> updateArticle(@Body ArticlesItem req);
    @POST("/StockServerApi/api/oauth/token")
    Call<LoginResponse> login(@Body LoginRequest req);


}