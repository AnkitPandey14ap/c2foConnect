package com.example.c2foconnect.video.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AllConnectionsResponse{

	@SerializedName("data")
	private List<AllConnectionsDataItem> data;

	@SerializedName("errorMessage")
	private Object errorMessage;

	@SerializedName("statusCode")
	private int statusCode;

	public List<AllConnectionsDataItem> getData(){
		return data;
	}

	public Object getErrorMessage(){
		return errorMessage;
	}

	public int getStatusCode(){
		return statusCode;
	}
}