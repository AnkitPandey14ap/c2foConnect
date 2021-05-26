package com.example.c2foconnect.video.model;

import com.google.gson.annotations.SerializedName;

public class InitialiseChatData {

	@SerializedName("chat")
	private Chat chat;

	@SerializedName("user")
	private User user;

	public Chat getChat(){
		return chat;
	}

	public User getUser(){
		return user;
	}
}