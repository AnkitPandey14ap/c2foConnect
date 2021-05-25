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
import retrofit.http.Query;

public interface ApiInterface {

    @FormUrlEncoded // annotation used in POST type requests
    @POST("/cuser/login")     // API's endpoints
    public void registration(@Field("email") String email,
                             @Field("password") String password,
                             Callback<UserResponse> callback);

    @FormUrlEncoded // annotation used in POST type requests
    @POST("/chat")     // API's endpoints
    public void initialiseChat(@Field("selfUserId") String selfUserId,
                             @Field("otherUserId") String otherUserId,
                             Callback<InitialiseChatResponse> callback);

    // API's endpoints
    @GET("/cuser")
    public void getUsers(
            Callback<UserResponse> callback);

    @GET("/cuser/videos")
    public void getStories(@Query("userId") String userId,
            Callback<StoryResponse> callback);


//    @FormUrlEncoded // annotation used in POST type requests
    @GET("/chat")
    public void getConnections(@Query("id") String id,
                               Callback<AllConnectionsResponse> callback);


}