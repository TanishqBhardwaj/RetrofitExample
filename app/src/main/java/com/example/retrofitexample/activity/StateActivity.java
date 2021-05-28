package com.example.retrofitexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.retrofitexample.network.JsonApiHolder;
import com.example.retrofitexample.model.LatestCasesModel;
import com.example.retrofitexample.R;
import com.example.retrofitexample.model.RegionalData;
import com.example.retrofitexample.adapter.StateAdapter;
import com.example.retrofitexample.network.RetrofitClass;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StateActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private JsonApiHolder jsonApiHolder;
    private List<RegionalData> stateWiseCaseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        setView();

        jsonApiHolder = RetrofitClass.getRetrofitInstance().create(JsonApiHolder.class);
        getLatestCases();
    }

    private void setView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        StateAdapter stateAdapter = new StateAdapter(stateWiseCaseList);
        recyclerView.setAdapter(stateAdapter);

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
                            stateAdapter.setOnItemClickListener(() -> {
                                Intent intent = new Intent(StateActivity.this,
                                        MainActivity.class);
                                startActivity(intent);
                            });
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
}