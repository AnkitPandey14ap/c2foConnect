package com.example.c2foconnect.video.model;

import com.google.gson.annotations.SerializedName;

public class ChatsItem{

	@SerializedName("userId2")
	private String userId2;

	@SerializedName("__v")
	private int V;

	@SerializedName("_id")
	private String id;

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