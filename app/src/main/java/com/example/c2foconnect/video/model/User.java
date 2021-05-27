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

	@SerializedName("industry")
	private String industry;

	@SerializedName("serviceArea")
	private String serviceArea;


	@SerializedName("annualRevenue")
	private String annualRevenue;

	@SerializedName("numberOfEmployees")
	private String numberOfEmployees;

	@SerializedName("yearOfEstablishment")
	private String yearOfEstablishment;

	@SerializedName("ceo")
	private String ceo;

	@SerializedName("officeAddress")
	private String officeAddress;

	@SerializedName("website")
	private String website;

	@SerializedName("coverImageUrl")
	private String coverImageUrl;



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

	public String getIndustry() {
		return industry;
	}

	public String getServiceArea() {
		return serviceArea;
	}

	public String getAnnualRevenue() {
		return annualRevenue;
	}

	public String getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public String getYearOfEstablishment() {
		return yearOfEstablishment;
	}

	public String getCeo() {
		return ceo;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public String getWebsite() {
		return website;
	}

	public String getCoverImageUrl() {
		return coverImageUrl;
	}
}