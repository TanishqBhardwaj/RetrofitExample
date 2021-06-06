package com.example.retrofitexample.model;

import com.example.retrofitexample.model.Data;

public class LatestCasesModel {

    private Data data;

    private String lastRefreshed;

    public LatestCasesModel() {}

    public LatestCasesModel(Data data, String lastRefreshed) {
        this.data = data;
        this.lastRefreshed = lastRefreshed;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public void setLastRefreshed(String lastRefreshed) {
        this.lastRefreshed = lastRefreshed;
    }
}
