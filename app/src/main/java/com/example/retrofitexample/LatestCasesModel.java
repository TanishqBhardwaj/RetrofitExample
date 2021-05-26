package com.example.retrofitexample;

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
