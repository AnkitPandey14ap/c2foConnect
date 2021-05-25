package com.example.c2foconnect.videos

import android.app.Activity
import android.app.ProgressDialog
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
import android.widget.Toast
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import com.example.c2foconnect.R
import com.example.c2foconnect.api.Api
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.video.model.InitialiseChatResponse
import com.example.c2foconnect.video.model.Story
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
import retrofit.RetrofitError
import retrofit.client.Response


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
            var data = it.getParcelable<Story>("data")
//            initializePlayer(data!!)
            data?.let { it1 -> initializePlayer(it1) }

            data?.let { it1 -> initView(it1) }
            initListneres(data)
        }


    }

    private fun initListneres(data: Story?) {
        connectTV.setOnClickListener({
            data?.userId?.let { it1 -> initialseConnection(it1) }
        })
    }


    private fun initView(userBean: Story) {
        clientNameTV.text = userBean.userName
        var tag = ""
        userBean.tags?.forEach { it -> tag += "$it " }
        clientDiscriptionTV.text = tag
        userBean.userProfileImage?.let { setRoundImage(it) }
    }

    private fun setRoundImage(url: String) {
        Picasso.with(context).load(url)
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


    private fun initializePlayer(data: Story) {

        exoplayerView.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, 12f)
            }
        }

        exoplayerView.clipToOutline = true

        simpleExoplayer = SimpleExoPlayer.Builder(activity as Context).build()
        val randomUrl = data.url
        randomUrl?.let { preparePlayer(it, "default") }


//        val randomUrl = urlList.random()
//        preparePlayer(randomUrl.first, randomUrl.second)
        exoplayerView.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
        simpleExoplayer.addListener(this)
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        Log.i("ExoPlayer", "onPlayerStateChanged: $playbackState")
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
        simpleExoplayer?.playWhenReady = true
        simpleExoplayer?.playbackState
    }

    override fun onPlayerError(error: ExoPlaybackException) {
        // handle error
    }


    private fun initialseConnection(clientId: String) {
        val progressDialog = ProgressDialog(context)
        progressDialog.setCancelable(false) // set cancelable to false
        progressDialog.setMessage("Please Wait") // set message
        progressDialog.show() // show progress dialog

        val user = context?.let { BPreference.getUser(it) }
        Api.getClient().initialiseChat(
            user?.id,
            clientId,
            object : retrofit.Callback<InitialiseChatResponse?> {
                override fun success(
                    initialiseChatResponse: InitialiseChatResponse?,
                    response: Response
                ) {
                    progressDialog.dismiss() //dismiss progress dialog
                    Log.i(TAG, "success: " + initialiseChatResponse?.data?.id)

                    initialiseChatResponse?.data?.id?.let {
                        ActivityHelper.openChatActivity(
                            context as Activity,
                            it, "send Name", "url"
                        )
                    }

                }

                override fun failure(error: RetrofitError) {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                    Log.i(TAG, "failure: $error.toString()")
                    progressDialog.dismiss() //dismiss progress dialog
                }

            })
    }


    companion object {
        fun newInstance(data: Story): StoryFragment? {
            var bundel = Bundle()
            bundel.putParcelable("data", data)
            var fragment = StoryFragment()
            fragment.arguments = bundel
            return fragment
        }
    }
}