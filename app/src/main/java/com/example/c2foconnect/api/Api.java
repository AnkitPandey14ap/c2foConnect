package com.example.c2foconnect.api;

import retrofit.RestAdapter;

public class Api {

    public static ApiInterface getClient() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://3.21.245.46:3000") //Set the Root URL
                .build(); //Finally building the adapter

        ApiInterface api = adapter.create(ApiInterface.class);
        return api; // return the APIInterface object
    }
}