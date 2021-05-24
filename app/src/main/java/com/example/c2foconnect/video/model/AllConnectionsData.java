package com.example.c2foconnect.video.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AllConnectionsData {

	@SerializedName("chats")
	private List<ChatsItem> chats;

	@SerializedName("users")
	private List<UsersItem> users;

	public List<ChatsItem> getChats(){
		return chats;
	}

	public List<UsersItem> getUsers(){
		return users;
	}
}