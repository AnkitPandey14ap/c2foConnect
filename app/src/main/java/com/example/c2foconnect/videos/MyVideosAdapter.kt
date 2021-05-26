package com.example.c2foconnect.videos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.c2foconnect.R
import com.example.c2foconnect.connectins.Contact
import com.example.c2foconnect.video.model.MyVideosItem
import com.squareup.picasso.Picasso

class MyVideosAdapter(private val mContacts: MutableList<MyVideosItem>?= mutableListOf()) :
    RecyclerView.Adapter<MyVideosAdapter.ViewHolder>() {

    val UPLOAD_VIEW = 0
    val VIDEO_VIEW = 1

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val viewTV = itemView.findViewById<TextView>(R.id.viewTV)
        val thumbnailIV = itemView.findViewById<ImageView>(R.id.thumbnailIV)
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
        if(viewHolder.itemViewType==VIDEO_VIEW){
            val videoData: MyVideosItem = this.mContacts!![position]

            val viewTV = viewHolder.viewTV

            val last = (100..9000).shuffled().last()
            viewTV.text =last.toString()
            val thumbnailIV=viewHolder.thumbnailIV

            Picasso.with(viewHolder.thumbnailIV.context)
                .load(videoData.url)
                .into(thumbnailIV);
        }
    }

    override fun getItemCount(): Int {
        return mContacts!!.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position  == 0) {
            return UPLOAD_VIEW
        } else {
            return VIDEO_VIEW

        }
    }
}