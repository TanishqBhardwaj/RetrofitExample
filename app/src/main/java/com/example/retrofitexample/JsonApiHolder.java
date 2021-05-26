package com.example.retrofitexample;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonApiHolder {

    @GET("/posts")
    Call<List<PostModel>> getPosts();

    @GET("/posts/{id}")
    Call<PostModel> getPostAtId(@Path("id") String mId);

    @GET("/covid19-in/stats/latest")
    Call<LatestCasesModel> getLatestCases();
}
