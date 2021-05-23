package com.example.c2foconnect.connectins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.c2foconnect.R

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        initUI()
    }

    private fun initUI() {
        val rvMessage = findViewById<View>(R.id.messageRV) as RecyclerView
        var contacts = Contact.createContactsList(20)
        val adapter = MessageAdapter(contacts)
        rvMessage.adapter = adapter
        rvMessage.layoutManager = LinearLayoutManager(this)
    }
}
