package com.example.sabaynews.Interface;

import com.example.sabaynews.models.HomeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
public interface APIInterface {
    @GET("/StockServerApi/api/public/cms/home/data")
    Call<HomeResponse> getDataInHomePage();
}