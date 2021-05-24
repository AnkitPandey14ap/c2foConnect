package com.example.c2foconnect.api;

import com.example.c2foconnect.video.model.AllConnectionsResponse;
import com.example.c2foconnect.video.model.InitialiseChatResponse;
import com.example.c2foconnect.video.model.StoryResponse;
import com.example.c2foconnect.video.model.UserResponse;

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

    @FormUrlEncoded // annotation used in POST type requests
    @POST("/chat")     // API's endpoints
    public void initialiseChat(@Field("userId1") String userId1,
                             @Field("userId2") String userId2,
                             Callback<InitialiseChatResponse> callback);

    // API's endpoints
    @GET("/cuser")
    public void getUsers(
            Callback<UserResponse> callback);

    @GET("/cuser/videos")
    public void getStories(
            Callback<StoryResponse> callback);


    @FormUrlEncoded // annotation used in POST type requests
    @POST("/chat")
    public void getConnections(@Field("id") String id,
                               Callback<AllConnectionsResponse> callback);


}