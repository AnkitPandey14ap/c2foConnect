package com.example.c2foconnect.connectins

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.c2foconnect.BFirebase
import com.example.c2foconnect.R
import com.example.c2foconnect.api.Api
import com.example.c2foconnect.base.BaseActivity
import com.example.c2foconnect.helper.*
import com.example.c2foconnect.video.model.ChatMessageModal
import com.example.c2foconnect.video.model.User
import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.activity_chat.*
import retrofit.RetrofitError
import retrofit.client.Response
import java.net.URLEncoder
import java.util.concurrent.TimeUnit


class ChatActivity : BaseActivity(), ChatCallBack {

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
        val phone = bundle?.getString(PHONE, "+919716114191")
        val email = bundle?.getString(EMAIL, "ankit.pandey@c2fo.com")

        user = BPreference.getUser(this)!!
        firebase = BFirebase(connectionId!!)
        firebase.subscribeRealTimeDatabase(this)


        initUI(clientId, clientName, clientProfileUrl, phone, email)


    }

    private fun initUI(
        clientId: String?,
        clientName: String?,
        clientProfileUrl: String?,
        phone: String?,
        email: String?
    ) {
        clientNameTV.text = clientName
        clientProfileUrl?.let { ImageHelper.setRoundImage(this, clientProfileIV, it, 72) }
        initChatListUI()

        RxView.clicks(sendIV).throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe { empty: Void? ->
                val text = msgET.text.toString().trim()
                msgET.setText("")
                clientId?.let { sendChatMessage(it, text) }
            }
        backIV.setOnClickListener {
            onBackPressed()
        }
        callIV.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_DIAL,
                    Uri.fromParts("tel", phone, null)
                )
            )
        }

        mailIV.setOnClickListener({
            try {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + email))
                intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject")
                intent.putExtra(Intent.EXTRA_TEXT, "your_text")
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
            }
            Log.i(TAG, "initUI: ")
        })

        whatsappIV.setOnClickListener {
            val packageManager: PackageManager = getPackageManager()
            val i = Intent(Intent.ACTION_VIEW)

            try {
                val url =
                    "https://api.whatsapp.com/send?phone=" + phone.toString() + "&text=" + URLEncoder.encode(
                        "Hi there",
                        "UTF-8"
                    )
                i.setPackage("com.whatsapp")
                i.data = Uri.parse(url)
                if (i.resolveActivity(packageManager) != null) {
                    startActivity(i)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

    private fun sendChatMessage(clientId: String, text: String) {
        if (!BUtility.isStringEmpty(text)) {
            sendMsgOnServer(text, user.id, clientId)
            firebase.sendMessage(text, user.id, this)
        }
    }

    private fun sendMsgOnServer(text: String, id: String, clientId: String) {
        Log.i(TAG, "sendMsgOnServer: " + text + " " + id + " " + clientId)
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
        val PHONE = "phone"
        val EMAIL = "email"
    }
}
