package com.example.c2foconnect.video.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Story: Parcelable {
    @SerializedName("userProfileImage")
    val userProfileImage: String? = null

    @SerializedName("userEmail")
    val userEmail: String? = null

    @SerializedName("userName")
    val userName: String? = null

    @SerializedName("userId")
    val userId: String? = null

    @SerializedName("url")
    val url: String? = null

    @SerializedName("tags")
    val tags: List<String>? = null

}