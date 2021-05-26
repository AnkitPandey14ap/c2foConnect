package com.example.c2foconnect.connectins

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.c2foconnect.BFirebase
import com.example.c2foconnect.R
import com.example.c2foconnect.api.Api
import com.example.c2foconnect.helper.*
import com.example.c2foconnect.video.model.ChatMessageModal
import com.example.c2foconnect.video.model.User
import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.activity_chat.*
import retrofit.RetrofitError
import retrofit.client.Response
import java.util.concurrent.TimeUnit


class ChatActivity : AppCompatActivity(), ChatCallBack {

    private lateinit var rvMessage: RecyclerView
    private lateinit var adapter: MessageAdapter
    private lateinit var firebase: BFirebase
    private lateinit var user: User

    private val TAG = "ChatActivity"

    private var messages = mutableListOf<ChatMessageModal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val bundle = intent.extras
        val connectionId = bundle?.getString(CONNECTION_ID, "78364873648372")
        val clientName = bundle?.getString(CLIENT_NAME, "Client")
        val clientProfileUrl = bundle?.getString(CLIENT_PROFILE_URL, "")
        val clientId = bundle?.getString(CLIENT_ID, "")

        user = BPreference.getUser(this)!!
        firebase = BFirebase(connectionId!!)
        firebase.subscribeRealTimeDatabase(this)


        initUI(clientId, clientName, clientProfileUrl)


    }

    private fun initUI(clientId: String?, clientName: String?, clientProfileUrl: String?) {
        clientNameTV.text = clientName
        clientProfileUrl?.let { ImageHelper.setRoundImage(this, clientProfileIV, it, 72) }
        initChatListUI()

        RxView.clicks(sendIV).throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe { empty: Void? ->
                val text = msgET.text.toString().trim()
                msgET.setText("")
                clientId?.let { sendChatMessage(it, text) }
            }
    }

    private fun sendChatMessage(clientId: String, text: String) {
        if (!BUtility.isStringEmpty(text)) {
            sendMsgOnServer(text, user.id, clientId)
            firebase.sendMessage(text, user.id, this)
        }
    }

    private fun sendMsgOnServer(text: String, id: String, clientId: String) {
        Log.i(TAG, "sendMsgOnServer: "+text+" "+id+" "+clientId)
        Api.getClient().sendMessage(
            id,
            clientId,
            text,
            object : retrofit.Callback<String?> {
                override fun success(
                    initialiseChatResponse: String?,
                    response: Response
                ) {
                    Log.i(TAG, "sendMsgOnServer success: ")

                }

                override fun failure(error: RetrofitError) {
                    Log.i(TAG, "sendMsgOnServer failure: $error.toString()")
                }

            })

    }

    private fun initChatListUI() {
        rvMessage = findViewById<View>(R.id.messageRV) as RecyclerView
        adapter = MessageAdapter(messages, user.id)
        rvMessage.adapter = adapter
        rvMessage.layoutManager = LinearLayoutManager(this)
    }

    override fun newMessageReceived(newMsg: ChatMessageModal) {
        Log.i(TAG, "newMessageReceived: " + newMsg.message)
        messages.add(newMsg)
        adapter.notifyItemChanged(messages.size - 1)
        rvMessage.scrollToPosition(messages.size - 1)

    }

    override fun onSendMessageSuccess(newMsg: ChatMessageModal?) {
//        msgET.setText("")
    }

    override fun onSendMessageFailure(newMsg: ChatMessageModal?) {
        if (newMsg != null) {
            msgET.setText(newMsg.message)
        }
        BToastHelper.somethingWentWrong(this)
    }


    companion object {
        val CONNECTION_ID = "connection_id"
        val CLIENT_NAME = "client_name"
        val CLIENT_PROFILE_URL = "client_profile_url"
        val CLIENT_ID = "client_id"
    }
}
