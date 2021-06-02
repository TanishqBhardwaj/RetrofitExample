package com.example.retrofitexample.network;

import com.example.retrofitexample.model.GlobalCasesModel;
import com.example.retrofitexample.model.LatestCasesModel;
import com.example.retrofitexample.model.PostModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonApiHolder {

    @GET("/covid19-in/stats/latest")
    Call<LatestCasesModel> getLatestCases();

    @GET("/summary")
    Call<GlobalCasesModel> getGlobalCases();
}
