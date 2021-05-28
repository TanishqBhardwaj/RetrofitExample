package com.example.retrofitexample.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "state_table")
public class StateEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String stateName;
    private int totalCase;
    private int recovered;
    private int deaths;
    private int active;

    public StateEntity() {}

    public StateEntity(String stateName, int totalCase, int recovered, int deaths, int active) {
        this.stateName = stateName;
        this.totalCase = totalCase;
        this.recovered = recovered;
        this.deaths = deaths;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getTotalCase() {
        return totalCase;
    }

    public void setTotalCase(int totalCase) {
        this.totalCase = totalCase;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
