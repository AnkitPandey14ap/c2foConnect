package com.example.c2foconnect.video.model;

import com.google.gson.annotations.SerializedName;

public class InitialiseChatResponse{

	@SerializedName("data")
	private InitialiseChatData data;

	@SerializedName("errorMessage")
	private Object errorMessage;

	@SerializedName("statusCode")
	private int statusCode;

	public InitialiseChatData getData(){
		return data;
	}

	public Object getErrorMessage(){
		return errorMessage;
	}

	public int getStatusCode(){
		return statusCode;
	}
}