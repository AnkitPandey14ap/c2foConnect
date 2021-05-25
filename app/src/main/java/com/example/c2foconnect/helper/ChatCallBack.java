package com.example.c2foconnect.helper;

import com.example.c2foconnect.video.model.ChatMessageModal;

public interface ChatCallBack {
    public void newMessageReceived(ChatMessageModal newMsg);

    public void onSendMessageSuccess(ChatMessageModal newMsg);

    public void onSendMessageFailure(ChatMessageModal newMsg);
}
