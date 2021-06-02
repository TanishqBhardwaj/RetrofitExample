package com.example.retrofitexample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitexample.model.LatestCasesModel;
import com.example.retrofitexample.model.Summary;
import com.example.retrofitexample.network.JsonApiHolder;
import com.example.retrofitexample.model.PostModel;
import com.example.retrofitexample.R;
import com.example.retrofitexample.network.RetrofitClass;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private JsonApiHolder jsonApiHolder;
    private TabLayout tabLayout;
    private TextView textViewConfirmedCases;
    private TextView textViewActiveCases;
    private TextView textViewDischargedCases;
    private TextView textViewDeaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonApiHolder = RetrofitClass.getRetrofitInstance().create(JsonApiHolder.class);

        setView();
        setClickListeners();
        fetchData();
    }

    private void setView() {
        tabLayout = findViewById(R.id.tabLayout);
        textViewConfirmedCases = findViewById(R.id.textViewConfirmedCases);
        textViewActiveCases = findViewById(R.id.textViewActiveCasesMain);
        textViewDischargedCases = findViewById(R.id.textViewDischargedCases);
        textViewDeaths = findViewById(R.id.textViewDeathCases);

        tabLayout.addTab(tabLayout.newTab().setText("Global"));
        tabLayout.addTab(tabLayout.newTab().setText("India"));
    }

    private void setClickListeners() {

    }

    private void fetchData() {
        Call<LatestCasesModel> latestCasesModelCall = jsonApiHolder.getLatestCases();
        latestCasesModelCall.enqueue(new Callback<LatestCasesModel>() {
            @Override
            public void onResponse(Call<LatestCasesModel> call, Response<LatestCasesModel> response) {
                if(response.isSuccessful()) {
                    setTextView(response.body().getData().getSummary());
                }
                else {
                    Toast.makeText(MainActivity.this, "Something went wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LatestCasesModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setTextView(Summary summary) {
        textViewConfirmedCases.setText(String.valueOf(summary.getTotal()));
        textViewActiveCases.setText(String.valueOf(
                summary.getTotal() - summary.getDischarged() - summary.getDeaths()
        ));
        textViewDischargedCases.setText(String.valueOf(summary.getDischarged()));
        textViewDeaths.setText(String.valueOf(summary.getDeaths()));
    }
}