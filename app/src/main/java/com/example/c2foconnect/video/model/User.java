package com.example.c2foconnect.video.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class User {

	@SerializedName("address")
	private String address;

	@SerializedName("companyDetails")
	private String companyDetails;

	@SerializedName("fcmId")
	private String fcmId;

	@SerializedName("myVideos")
	private List<MyVideosItem> myVideos;

	@SerializedName("products")
	private List<String> products;

	@SerializedName("requirements")
	private List<String> requirements;

	@SerializedName("password")
	private String password;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("_id")
	private String id;

	@SerializedName("businessType")
	private String businessType;

	@SerializedName("sector")
	private String sector;

	@SerializedName("profileImageUrl")
	private String profileImageUrl;

	@SerializedName("email")
	private String email;

	public String getAddress(){
		return address;
	}

	public String getCompanyDetails(){
		return companyDetails;
	}

	public String getFcmId(){
		return fcmId;
	}

	public List<MyVideosItem> getMyVideos(){
		return myVideos;
	}

	public List<String> getProducts(){
		return products;
	}
	public List<String> getRequirements(){
		return requirements;
	}

	public String getPassword(){
		return password;
	}

	public String getPhone(){
		return phone;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getBusinessType(){
		return businessType;
	}

	public String getSector(){
		return sector;
	}

	public String getProfileImageUrl(){
		return profileImageUrl;
	}

	public String getEmail(){
		return email;
	}
}