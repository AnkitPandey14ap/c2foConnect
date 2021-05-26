package com.example.c2foconnect.video.model;

import com.google.gson.annotations.SerializedName;

public class Chat{

	@SerializedName("userId1")
	private String userId1;

	@SerializedName("userId2")
	private String userId2;

	@SerializedName("__v")
	private int V;

	@SerializedName("_id")
	private String id;

	public String getUserId1(){
		return userId1;
	}

	public String getUserId2(){
		return userId2;
	}

	public int getV(){
		return V;
	}

	public String getId(){
		return id;
	}
}