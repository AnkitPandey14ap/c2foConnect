package com.example.c2foconnect.connectins

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.c2foconnect.R
import com.example.c2foconnect.helper.ActivityHelper

class ConnectionsAdapter(private val mContacts: List<Contact>) :
    RecyclerView.Adapter<ConnectionsAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val nameTV = itemView.findViewById<TextView>(R.id.userTV)
        val messageTV = itemView.findViewById<TextView>(R.id.msgTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectionsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_connection, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ConnectionsAdapter.ViewHolder, position: Int) {
        val contact: Contact = mContacts.get(position)

        val nameTV = viewHolder.nameTV
        nameTV.text = contact.name

        val messageTV = viewHolder.messageTV
        messageTV.text = contact.lastMsg

        viewHolder.itemView.setOnClickListener {
            ActivityHelper.openChatActivity(viewHolder.itemView.context as Activity)
        }
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }

}