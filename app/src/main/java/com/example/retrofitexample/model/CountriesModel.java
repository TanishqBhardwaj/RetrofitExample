package com.example.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

public class CountriesModel {

    @SerializedName("Country")
    private String countryName;

    @SerializedName("NewConfirmed")
    private int newConfirmedCases;

    @SerializedName("TotalConfirmed")
    private int totalConfirmedCases;

    @SerializedName("NewDeaths")
    private int newDeathsCases;

    @SerializedName("TotalDeaths")
    private int totalDeathsCases;

    @SerializedName("NewRecovered")
    private int newRecoveredCases;

    @SerializedName("TotalRecovered")
    private int totalRecoveredCases;

    public CountriesModel() {}

    public CountriesModel(String countryName, int newConfirmedCases, int totalConfirmedCases,
                          int newDeathsCases, int totalDeathsCases, int newRecoveredCases,
                          int totalRecoveredCases) {
        this.countryName = countryName;
        this.newConfirmedCases = newConfirmedCases;
        this.totalConfirmedCases = totalConfirmedCases;
        this.newDeathsCases = newDeathsCases;
        this.totalDeathsCases = totalDeathsCases;
        this.newRecoveredCases = newRecoveredCases;
        this.totalRecoveredCases = totalRecoveredCases;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getNewConfirmedCases() {
        return newConfirmedCases;
    }

    public void setNewConfirmedCases(int newConfirmedCases) {
        this.newConfirmedCases = newConfirmedCases;
    }

    public int getTotalConfirmedCases() {
        return totalConfirmedCases;
    }

    public void setTotalConfirmedCases(int totalConfirmedCases) {
        this.totalConfirmedCases = totalConfirmedCases;
    }

    public int getNewDeathsCases() {
        return newDeathsCases;
    }

    public void setNewDeathsCases(int newDeathsCases) {
        this.newDeathsCases = newDeathsCases;
    }

    public int getTotalDeathsCases() {
        return totalDeathsCases;
    }

    public void setTotalDeathsCases(int totalDeathsCases) {
        this.totalDeathsCases = totalDeathsCases;
    }

    public int getNewRecoveredCases() {
        return newRecoveredCases;
    }

    public void setNewRecoveredCases(int newRecoveredCases) {
        this.newRecoveredCases = newRecoveredCases;
    }

    public int getTotalRecoveredCases() {
        return totalRecoveredCases;
    }

    public void setTotalRecoveredCases(int totalRecoveredCases) {
        this.totalRecoveredCases = totalRecoveredCases;
    }
}
