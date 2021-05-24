package com.example.c2foconnect.video.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MyVideosItem{

	@SerializedName("_id")
	private String id;

	@SerializedName("url")
	private String url;

	@SerializedName("tags")
	private List<String> tags;

	public String getId(){
		return id;
	}

	public String getUrl(){
		return url;
	}

	public List<String> getTags(){
		return tags;
	}
}