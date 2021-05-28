package com.example.retrofitexample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.retrofitexample.network.JsonApiHolder;
import com.example.retrofitexample.model.PostModel;
import com.example.retrofitexample.R;

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
    private TextView textViewTitle;
    private EditText editTextId;
    private Button buttonFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonApiHolder = getRetrofitInstance().create(JsonApiHolder.class);

        setView();
        setClickListeners();
//        getPosts();
//        getPostById("5");
    }

    private void setView() {
        textViewTitle = findViewById(R.id.textView2);
        editTextId = findViewById(R.id.editTextNumber);
        buttonFetch = findViewById(R.id.button);
    }

    private void setClickListeners() {
        buttonFetch.setOnClickListener(view -> {
            if(editTextId.getText().toString().trim().length() > 0) {
                getPostById(editTextId.getText().toString().trim());
            }
        });
    }

    private void getPosts() {
        Call<List<PostModel>> postList = jsonApiHolder.getPosts();
        postList.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(response.isSuccessful()) {
                    List<PostModel> postModelList = response.body();
                    for(PostModel postModel : postModelList) {
//                        Log.d("TAG", "onResponse: " +  postModel.getName());
                    }
                }
                else {
                    Log.e("TAG", "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getPostById(String id) {
        Call<PostModel> postAtId = jsonApiHolder.getPostAtId(id);
        postAtId.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    textViewTitle.setText(response.body().getName());
                }
                else {
                    Log.e("TAG", "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
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
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}