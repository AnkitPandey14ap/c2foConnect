package com.example.c2foconnect.videos

import android.content.Context
import android.graphics.Outline
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import com.example.c2foconnect.R
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.video.model.StoriesDataModel
import com.example.c2foconnect.video.model.UserBean
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_story.*


class StoryFragment : Fragment(), Player.EventListener {

    val TAG = "PagerFragment";


    private lateinit var simpleExoplayer: SimpleExoPlayer
    private var playbackPosition: Long = 0
//    private val mp4Url = "https://html5demos.com/assets/dizzy.mp4"
//    private val dashUrl = "https://storage.googleapis.com/wvmedia/clear/vp9/tears/tears_uhd.mpd"
//    private val urlList = listOf(mp4Url to "default", dashUrl to "dash")

    override fun onResume() {
        startPlayer()
        super.onResume()
    }

    override fun onPause() {
        Log.i(TAG, "onPause: ")
        pausePlayer()
        super.onPause()
    }

    override fun onStop() {
        releasePlayer()
        super.onStop()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_story, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
//            fragmentContainer.setBackgroundResource(it.getInt("color"))
            var data = it.getParcelable<StoriesDataModel>("data")
//            initializePlayer(data!!)
            data?.let { it1 -> initializePlayer(it1) }
        }

        var user = BPreference.getUser(this.context!!)
        user?.let { initView(it) }
    }

    private fun initView(userBean: UserBean) {

        Picasso.with(context).load(userBean.imageUrl)
            .resize(42, 42)
            .into(clientIV, object : Callback {
                override fun onSuccess() {
                    val imageBitmap =
                        (clientIV.drawable as BitmapDrawable).bitmap
                    val imageDrawable =
                        RoundedBitmapDrawableFactory.create(resources, imageBitmap)
                    imageDrawable.isCircular = true
                    imageDrawable.cornerRadius = Math.max(
                        imageBitmap.width,
                        imageBitmap.height
                    ) / 2.0f
                    clientIV.setImageDrawable(imageDrawable)
                }

                override fun onError() {
                    clientIV.setImageResource(R.drawable.ic_follow_avatar_bottom_icon)
                }
            })
    }


    private fun initializePlayer(data: StoriesDataModel) {

        exoplayerView.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, 12f)
            }
        }

        exoplayerView.clipToOutline = true





        simpleExoplayer = SimpleExoPlayer.Builder(activity as Context).build()
        val randomUrl = data.storyUrl
        preparePlayer(randomUrl, "default")


//        val randomUrl = urlList.random()
//        preparePlayer(randomUrl.first, randomUrl.second)
        exoplayerView.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
        simpleExoplayer.addListener(this)
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        Log.i("Ankit", "onPlayerStateChanged: $playbackState")
//        if (playbackState == Player.STATE_BUFFERING)
//            progressBar.visibility = View.VISIBLE
//        else if (playbackState == Player.STATE_READY || playbackState == Player.STATE_ENDED)
//            progressBar.visibility = View.INVISIBLE
    }

    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(context as Context, "exoplayer-sample")
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

    private fun preparePlayer(videoUrl: String, type: String) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri, type)
        simpleExoplayer.prepare(mediaSource)
    }

    private fun releasePlayer() {
        playbackPosition = simpleExoplayer.currentPosition
        simpleExoplayer.release()
    }


    private fun pausePlayer() {
        simpleExoplayer.playWhenReady = false
        simpleExoplayer.playbackState
    }

    private fun startPlayer() {
        simpleExoplayer.playWhenReady = true
        simpleExoplayer.playbackState
    }

    override fun onPlayerError(error: ExoPlaybackException) {
        // handle error
    }


    companion object {
        fun newInstance(data: StoriesDataModel): StoryFragment? {
            var bundel = Bundle()
            bundel.putParcelable("data", data)
            var fragment = StoryFragment()
            fragment.arguments = bundel
            return fragment
        }
    }
}