package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class RegionalData {

    @SerializedName("loc")
    private String stateName;
    private int confirmedCasesIndian;
    private int confirmedCasesForeign;
    private int discharged;
    private int deaths;
    private int totalConfirmed;

    public RegionalData() {}

    public RegionalData(String stateName, int confirmedCasesIndian, int confirmedCasesForeign,
                        int discharged, int deaths, int totalConfirmed) {
        this.stateName = stateName;
        this.confirmedCasesIndian = confirmedCasesIndian;
        this.confirmedCasesForeign = confirmedCasesForeign;
        this.discharged = discharged;
        this.deaths = deaths;
        this.totalConfirmed = totalConfirmed;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getConfirmedCasesIndian() {
        return confirmedCasesIndian;
    }

    public void setConfirmedCasesIndian(int confirmedCasesIndian) {
        this.confirmedCasesIndian = confirmedCasesIndian;
    }

    public int getConfirmedCasesForeign() {
        return confirmedCasesForeign;
    }

    public void setConfirmedCasesForeign(int confirmedCasesForeign) {
        this.confirmedCasesForeign = confirmedCasesForeign;
    }

    public int getDischarged() {
        return discharged;
    }

    public void setDischarged(int discharged) {
        this.discharged = discharged;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }
}
