package com.example.retrofitexample.network;

import com.example.retrofitexample.model.GlobalCasesModel;
import com.example.retrofitexample.model.LatestCasesModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApiHolder {

    @GET("/covid19-in/stats/latest")
    Call<LatestCasesModel> getLatestCases();

    @GET("/summary")
    Call<GlobalCasesModel> getGlobalCases();
}
