package com.example.retrofitexample.model;

import java.util.List;

public class Data {

    private Summary summary;

    private List<RegionalData> regional;

    public Data() {}

    public Data(Summary summary, List<RegionalData> regional) {
        this.summary = summary;
        this.regional = regional;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<RegionalData> getRegional() {
        return regional;
    }

    public void setRegional(List<RegionalData> regional) {
        this.regional = regional;
    }
}
