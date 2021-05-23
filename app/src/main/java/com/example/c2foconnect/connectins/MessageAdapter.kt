package com.example.c2foconnect.connectins

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.c2foconnect.R

class MessageAdapter(private val mContacts: List<Contact>) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    val LEFT_CHAT = 0
    val RIGHT_CHAT = 1

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val messageTV = itemView.findViewById<TextView>(R.id.messageTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        if (viewType == LEFT_CHAT) {
            val contactView = inflater.inflate(R.layout.item_message_left, parent, false)
            return ViewHolder(contactView)
        } else {
            val contactView = inflater.inflate(R.layout.item_message_right, parent, false)
            return ViewHolder(contactView)
        }
    }

    override fun onBindViewHolder(viewHolder: MessageAdapter.ViewHolder, position: Int) {
        val contact: Contact = mContacts.get(position)

        val messageTV = viewHolder.messageTV
//        messageTV.text = contact.lastMsg
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0) {
            return LEFT_CHAT
        } else {
            return RIGHT_CHAT

        }
    }
}