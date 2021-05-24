package com.example.c2foconnect.video.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoryResponse {

    @SerializedName("data")
    private List<Story> data;

    @SerializedName("errorMessage")
    private Object errorMessage;

    @SerializedName("statusCode")
    private int statusCode;

    public List<Story> getData() {
        return data;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }
}