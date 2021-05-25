package com.example.c2foconnect.video.model;

import com.google.gson.annotations.SerializedName;

public class ChatMessageModal {

    @SerializedName("timeStamp")
    private String timeStamp;

    @SerializedName("message")
    private String message;

    @SerializedName("userId")
    private String userId;

    @SerializedName("chatId")
    private String chatId;

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserId(String userId) {
        this.userId=userId;
    }

    public void setChatId(String chatId) {
        this.chatId=chatId;
    }
}