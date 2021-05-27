package com.example.c2foconnect.videos

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.c2foconnect.R
import com.example.c2foconnect.video.model.MyVideosItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class MyVideosAdapter(
    var context: Context,
    private val mContacts: MutableList<MyVideosItem>? = mutableListOf()
) :
    RecyclerView.Adapter<MyVideosAdapter.ViewHolder>() {

    val UPLOAD_VIEW = 0
    val VIDEO_VIEW = 1
    private var playbackPosition: Long = 1


    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val viewTV = itemView.findViewById<TextView>(R.id.viewTV)
        val thumbnailIV = itemView.findViewById<PlayerView>(R.id.thumbnailIVExoPlayer)
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
        if (viewHolder.itemViewType == VIDEO_VIEW) {
            val videoData: MyVideosItem = this.mContacts!![position]

            val viewTV = viewHolder.viewTV

            val last = (10..100).random()
            viewTV.text = last.toString()
            val thumbnailIV = viewHolder.thumbnailIV

//            Picasso.with(viewHolder.thumbnailIV.context)
//                .load(videoData.url)
//                .into(thumbnailIV);


            initializePlayer(videoData, thumbnailIV)

        }


    }

    override fun getItemCount(): Int {
        return mContacts!!.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return UPLOAD_VIEW
        } else {
            return VIDEO_VIEW

        }
    }

    private fun initializePlayer(
        data: MyVideosItem,
        exoplayerView: PlayerView
    ) {


//        exoplayerView.outlineProvider = object : ViewOutlineProvider() {
//            override fun getOutline(view: View, outline: Outline) {
//                outline.setRoundRect(0, 0, view.width, view.height, 12f)
//            }
//        }

//        exoplayerView.clipToOutline = true

        var simpleExoplayer: SimpleExoPlayer = SimpleExoPlayer.Builder(context).build()
        val randomUrl = data.url
//        val randomUrl = "https://html5demos.com/assets/dizzy.mp4"
        randomUrl?.let { preparePlayer(it, "default", simpleExoplayer) }

        exoplayerView.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
//        simpleExoplayer.playWhenReady = true
//        simpleExoplayer?.playbackState
    }

    private fun preparePlayer(
        videoUrl: String,
        type: String,
        simpleExoplayer: SimpleExoPlayer

    ) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri, type)
        simpleExoplayer.prepare(mediaSource)
    }

    private fun releasePlayer() {
//        playbackPosition = simpleExoplayer.currentPosition
//        simpleExoplayer.release()
    }

    private fun buildMediaSource(uri: Uri, type: String): MediaSource {
        return if (type == "dash") {
            DashMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri)
        } else {
            ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri)
        }
    }

    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(context as Context, "exoplayer-sample")
    }


}