package com.example.c2foconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.c2foconnect.connectins.Contact
import com.example.c2foconnect.connectins.ContactsAdapter

class ConnectionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connections)

        initUI();
    }

    private fun initUI() {
        val rvContacts = findViewById<View>(R.id.connectionRV) as RecyclerView
        var contacts = Contact.createContactsList(20)
        val adapter = ContactsAdapter(contacts)
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(this)
    }
}