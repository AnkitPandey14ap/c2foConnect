package com.example.c2foconnect.connectins

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.c2foconnect.BFirebase
import com.example.c2foconnect.R
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.helper.BToastHelper
import com.example.c2foconnect.helper.BUtility
import com.example.c2foconnect.helper.ChatCallBack
import com.example.c2foconnect.video.model.ChatMessageModal
import com.example.c2foconnect.video.model.User
import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.concurrent.TimeUnit


class ChatActivity : AppCompatActivity(), ChatCallBack {

    private lateinit var rvMessage: RecyclerView
    private lateinit var adapter: MessageAdapter
    private lateinit var firebase: BFirebase
    private lateinit var user: User
    private val CONNECTION_ID = "connection_id"
    private val TAG = "Ankit"

    private var messages = mutableListOf<ChatMessageModal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val bundle = intent.extras
        val connectionId = bundle?.getString(CONNECTION_ID, "78364873648372")

        user = BPreference.getUser(this)!!
        firebase = BFirebase(connectionId!!)
        firebase.subscribeRealTimeDatabase(this)


        initUI()


    }

    private fun initUI() {
        initChatListUI()

        RxView.clicks(sendIV).throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe { empty: Void? ->
                val text = msgET.text.toString().trim()
                msgET.setText("")
                sendChatMessage(text)
            }
    }

    private fun sendChatMessage(text: String) {
        if (!BUtility.isStringEmpty(text)) {
            firebase.sendMessage(text, user.id, this)
        }
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
}
