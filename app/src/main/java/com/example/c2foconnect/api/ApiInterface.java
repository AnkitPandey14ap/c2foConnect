package com.example.c2foconnect.api;

import com.example.c2foconnect.video.model.SignUpResponse;
import com.example.c2foconnect.video.model.UserListResponse;
import com.example.c2foconnect.video.model.UserResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiInterface {

    @FormUrlEncoded // annotation used in POST type requests
    @POST("/cuser")     // API's endpoints
    public void registration(@Field("email") String email,
                             @Field("password") String password,
                             Callback<UserResponse> callback);

    // API's endpoints
    @GET("/cuser")
    public void getUsers(
            Callback<UserResponse> callback);
}