package com.example.c2foconnect.videos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.c2foconnect.R
import com.example.c2foconnect.connectins.Contact

class MyVideosAdapter(private val mContacts: List<Contact>) :
    RecyclerView.Adapter<MyVideosAdapter.ViewHolder>() {

    val UPLOAD_VIEW = 0
    val VIDEO_VIEW = 1

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
//        val messageTV = itemView.findViewById<TextView>(R.id.messageTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVideosAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        if (viewType == UPLOAD_VIEW) {
            val contactView = inflater.inflate(R.layout.item_video_upload, parent, false)
            return ViewHolder(contactView)
        } else {
            val contactView = inflater.inflate(R.layout.item_my_video, parent, false)
            return ViewHolder(contactView)
        }
    }

    override fun onBindViewHolder(viewHolder: MyVideosAdapter.ViewHolder, position: Int) {
        val contact: Contact = mContacts.get(position)

//        val messageTV = viewHolder.messageTV
//        messageTV.text = contact.lastMsg
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position  == 0) {
            return UPLOAD_VIEW
        } else {
            return VIDEO_VIEW

        }
    }
}