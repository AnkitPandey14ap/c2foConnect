package com.example.c2foconnect.video.model;

import com.google.gson.annotations.SerializedName;

public class AllConnectionsDataItem {

	@SerializedName("chatId")
	private String chatId;

	@SerializedName("userId")
	private String userId;

	@SerializedName("user")
	private User user;

	public String getChatId(){
		return chatId;
	}

	public String getUserId(){
		return userId;
	}

	public User getUser(){
		return user;
	}
}