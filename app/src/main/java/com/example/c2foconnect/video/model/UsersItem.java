package com.example.c2foconnect.video.model;

import com.google.gson.annotations.SerializedName;

public class UsersItem{

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("_id")
	private String id;

	@SerializedName("profileImageUrl")
	private String profileImageUrl;

	@SerializedName("email")
	private String email;

	public String getPhone(){
		return phone;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getProfileImageUrl(){
		return profileImageUrl;
	}

	public String getEmail(){
		return email;
	}
}