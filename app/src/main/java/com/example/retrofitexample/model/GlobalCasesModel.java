package com.example.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GlobalCasesModel {

    @SerializedName("Global")
    private Global global;

    @SerializedName("Countries")
    private List<CountriesModel> countries;

    public GlobalCasesModel() {}

    public GlobalCasesModel(Global global, List<CountriesModel> countries) {
        this.global = global;
        this.countries = countries;
    }

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public List<CountriesModel> getCountries() {
        return countries;
    }

    public void setCountries(List<CountriesModel> countries) {
        this.countries = countries;
    }
}
