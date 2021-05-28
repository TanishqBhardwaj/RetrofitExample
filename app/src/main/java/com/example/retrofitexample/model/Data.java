package com.example.retrofitexample.model;

import java.util.List;

public class Data {

    private List<RegionalData> regional;

    public Data() {}

    public Data(List<RegionalData> regional) {
        this.regional = regional;
    }

    public List<RegionalData> getRegional() {
        return regional;
    }

    public void setRegional(List<RegionalData> regional) {
        this.regional = regional;
    }
}
