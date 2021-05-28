package com.example.retrofitexample.model;

import com.example.retrofitexample.model.Data;

public class LatestCasesModel {

    private Data data;

    public LatestCasesModel() {}

    public LatestCasesModel(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
