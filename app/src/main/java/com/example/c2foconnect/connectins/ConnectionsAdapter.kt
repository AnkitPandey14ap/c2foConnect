package com.example.c2foconnect.connectins

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.c2foconnect.R
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.helper.ImageHelper
import com.example.c2foconnect.video.model.AllConnectionsDataItem
import com.example.c2foconnect.video.model.User
import kotlinx.android.synthetic.main.activity_chat.*

class ConnectionsAdapter(private val connections: MutableList<AllConnectionsDataItem>? = mutableListOf()) :
    RecyclerView.Adapter<ConnectionsAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val nameTV = itemView.findViewById<TextView>(R.id.userTV)
        val messageTV = itemView.findViewById<TextView>(R.id.msgTV)
        val userIV = itemView.findViewById<ImageView>(R.id.userIV)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConnectionsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_connection, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ConnectionsAdapter.ViewHolder, position: Int) {
        val user: User = connections!![position].user

        val nameTV = viewHolder.nameTV
        nameTV.text = user.name

        val messageTV = viewHolder.messageTV
        val userIV = viewHolder.userIV
        messageTV.text = user.email

        connections[position].user.profileImageUrl?.let { ImageHelper.setRoundImage(userIV.context, userIV, it, 72) }


        viewHolder.itemView.setOnClickListener {
            ActivityHelper.openChatActivity(
                viewHolder.itemView.context as Activity,
                connections[position].chatId,connections[position].user.name,connections[position].user.profileImageUrl,connections[position].userId
            )
        }
    }

    override fun getItemCount(): Int {
        return connections!!.size
    }

}