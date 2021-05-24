package com.example.c2foconnect.video.model;

public class UserResponse{
	private User data;
	private Object errorMessage;
	private int statusCode;

	public User getData(){
		return data;
	}

	public Object getErrorMessage(){
		return errorMessage;
	}

	public int getStatusCode(){
		return statusCode;
	}
}
