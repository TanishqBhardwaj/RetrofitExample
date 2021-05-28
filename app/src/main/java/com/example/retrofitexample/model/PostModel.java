package com.example.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

public class PostModel {

    private int userId;

    private int id;

    @SerializedName("title")
    private String name;

    private String body;

    public PostModel() {

    }

    public PostModel(int userId, int id, String name, String body) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
