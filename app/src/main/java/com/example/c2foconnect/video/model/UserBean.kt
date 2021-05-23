package com.example.c2foconnect.video.model

data class UserBean(
    val id: Long?,
    val name: String?,
    val imageUrl: String?,
    val email: String? = "emailId",
    val password: String? = "password"
)

