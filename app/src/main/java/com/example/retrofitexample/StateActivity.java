package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StateActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JsonApiHolder jsonApiHolder;
    private List<RegionalData> stateWiseCaseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        setView();

        jsonApiHolder = getRetrofitInstance().create(JsonApiHolder.class);
        getLatestCases();
    }

    private void setView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
//        List<String> stateList = new ArrayList<>();
//        stateList.add("Uttar Pradesh");
//        stateList.add("Delhi");
//        stateList.add("Rajasthan");
//        stateList.add("Maharashtra");
//        stateList.add("Karnataka");
//
//        StateAdapter stateAdapter = new StateAdapter(stateList);
//        recyclerView.setAdapter(stateAdapter);
    }

    private void getLatestCases() {
        Call<LatestCasesModel> latestCasesModel = jsonApiHolder.getLatestCases();

        latestCasesModel.enqueue(new Callback<LatestCasesModel>() {
                    @Override
                    public void onResponse(Call<LatestCasesModel> call, Response<LatestCasesModel> response) {
                        if(response.isSuccessful()) {
                            stateWiseCaseList.addAll(response.body().getData().getRegional());

                            StateAdapter stateAdapter = new StateAdapter(stateWiseCaseList);
                            recyclerView.setAdapter(stateAdapter);
                        }
                        else {
                            Toast.makeText(StateActivity.this, "Something Went Wrong!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LatestCasesModel> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private Retrofit getRetrofitInstance() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl("https://api.rootnet.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}