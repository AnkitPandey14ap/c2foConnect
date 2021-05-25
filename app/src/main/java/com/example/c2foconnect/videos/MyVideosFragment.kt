package com.example.c2foconnect.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.c2foconnect.R
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.video.model.MyVideosItem
import kotlinx.android.synthetic.main.fragment_my_videos.*


class MyVideosFragment : Fragment() {

    val TAG = "MyVideosFragment";
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_videos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        val user = context?.let { BPreference.getUser(it) }

        if (user != null && user.myVideos != null && user.myVideos.size > 0) {
            hideEmptyUI()
            initRecyclerView(user.myVideos)
        } else {
            showEmptyUI()
        }
    }

    private fun showEmptyUI() {
        emptyView.visibility = View.VISIBLE
        messageRV.visibility = View.GONE
    }

    private fun hideEmptyUI() {
        emptyView.visibility = View.GONE
        messageRV.visibility = View.VISIBLE
    }

    private fun initRecyclerView(myVideos: MutableList<MyVideosItem>) {
        val adapter = MyVideosAdapter(myVideos)
        messageRV.adapter = adapter
        messageRV.layoutManager = GridLayoutManager(context, 3)

    }


    companion object {
        fun newInstance(): MyVideosFragment? {
            return MyVideosFragment()
        }
    }

}