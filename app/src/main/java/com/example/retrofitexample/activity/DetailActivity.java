package com.example.retrofitexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.retrofitexample.local.StateDAO;
import com.example.retrofitexample.local.StateDatabase;
import com.example.retrofitexample.local.StateEntity;
import com.example.retrofitexample.model.CountriesModel;
import com.example.retrofitexample.model.GlobalCasesModel;
import com.example.retrofitexample.network.JsonApiHolder;
import com.example.retrofitexample.model.LatestCasesModel;
import com.example.retrofitexample.R;
import com.example.retrofitexample.model.RegionalData;
import com.example.retrofitexample.adapter.DetailAdapter;
import com.example.retrofitexample.network.RetrofitClass;
import com.example.retrofitexample.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private JsonApiHolder jsonApiHolder;
    private List<RegionalData> stateWiseCaseList = new ArrayList<>();
    private List<CountriesModel> countriesWiseCaseList = new ArrayList<>();

    private StateDatabase stateDatabase;
    private StateDAO stateDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setView();

        stateDatabase = StateDatabase.getInstance(this);
        stateDAO = stateDatabase.stateDAO();
        getLatestStateCases();
//        getLatestCountryCases();
    }

    private void setView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
//        DetailAdapter detailAdapter = new DetailAdapter(stateWiseCaseList);
//        recyclerView.setAdapter(detailAdapter);

    }

    private void getLatestStateCases() {
        jsonApiHolder = RetrofitClass.getRetrofitInstance(Constants.BASE_LOCAL_URL).create(JsonApiHolder.class);
        Call<LatestCasesModel> latestCasesModel = jsonApiHolder.getLatestCases();

        latestCasesModel.enqueue(new Callback<LatestCasesModel>() {
                    @Override
                    public void onResponse(Call<LatestCasesModel> call, Response<LatestCasesModel> response) {
                        if(response.isSuccessful()) {
                            stateWiseCaseList.addAll(response.body().getData().getRegional());

                            DetailAdapter detailAdapter = new DetailAdapter(stateWiseCaseList);
                            recyclerView.setAdapter(detailAdapter);
                            detailAdapter.setOnItemClickListener(() -> {
                                Intent intent = new Intent(DetailActivity.this,
                                        MainActivity.class);
                                startActivity(intent);
                            });
                            saveInDb();
                        }
                        else {
                            Toast.makeText(DetailActivity.this, "Something Went Wrong!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LatestCasesModel> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private void getLatestCountryCases() {
        jsonApiHolder = RetrofitClass.getRetrofitInstance(Constants.BASE_GLOBAL_URL).create(JsonApiHolder.class);
        Call<GlobalCasesModel> globalCasesModelCall = jsonApiHolder.getGlobalCases();

        globalCasesModelCall.enqueue(new Callback<GlobalCasesModel>() {
            @Override
            public void onResponse(Call<GlobalCasesModel> call, Response<GlobalCasesModel> response) {
                if(response.isSuccessful()) {
                    countriesWiseCaseList.addAll(response.body().getCountries());

                    DetailAdapter detailAdapter = new DetailAdapter(countriesWiseCaseList, true);
                    recyclerView.setAdapter(detailAdapter);
                }
            }

            @Override
            public void onFailure(Call<GlobalCasesModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void saveInDb() {
        for(RegionalData regionalData : stateWiseCaseList) {
            StateEntity stateEntity = new StateEntity(regionalData.getStateName(),
                    regionalData.getTotalConfirmed(),
                    regionalData.getDischarged(),
                    regionalData.getDeaths(),
                    regionalData.getTotalConfirmed() - regionalData.getDischarged() - regionalData.getDeaths());

            stateDAO.insert(stateEntity);
        }
    }
}