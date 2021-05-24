package com.example.c2foconnect.videos

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import com.example.c2foconnect.R
import com.example.c2foconnect.api.Api
import com.example.c2foconnect.video.model.StoriesDataModel
import com.example.c2foconnect.video.model.Story
import com.example.c2foconnect.video.model.StoryResponse
import kotlinx.android.synthetic.main.fragment_root.*
import retrofit.RetrofitError
import retrofit.client.Response


class RootFragment : Fragment(), OnPageChangeListener, ViewPager2.PageTransformer {

    val TAG = "Ankit";
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_root, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getStories()

    }

    private fun initViewPager(stories: MutableList<Story>) {
        viewPager2.adapter = ViewPagerAdapter()
        viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        val list = mutableListOf(
//            StoriesDataModel(1, "https://html5demos.com/assets/dizzy.mp4"),
            StoriesDataModel(0, "https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4"),
            StoriesDataModel(1, "https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4"),
            StoriesDataModel(2, "https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4"),
            StoriesDataModel(3, "https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4"),
            StoriesDataModel(4, "https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4"),
            StoriesDataModel(5, "https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4")
        )
        viewPager2.adapter = ViewPagerFragmentStateAdapter(this, stories)
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    // you are on the first page
                } else if (position == 1) {
                    // you are on the second page
                } else if (position == 2) {
                    // you are on the third page
                }
                super.onPageSelected(position)
            }


            override fun onPageScrollStateChanged(state: Int) {
                Log.i(TAG, "onPageScrollStateChanged: $state")
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.i(TAG, "onPageScrolled: $position")

            }
        })
    }


    companion object {
        fun newInstance(): RootFragment? {
            return RootFragment()
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
        Log.i(TAG, "onPageScrollStateChanged: $state")
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        Log.i(TAG, "onPageScrolled: $position")

    }

    override fun onPageSelected(position: Int) {
        Log.i(TAG, "onPageSelected: $position")

    }

    override fun transformPage(page: View, position: Float) {
        Log.i(TAG, "transformPage: " + page)
    }

    private fun getStories() {
        val progressDialog = ProgressDialog(context)
        progressDialog.setCancelable(false) // set cancelable to false
        progressDialog.setMessage("Please Wait") // set message
        progressDialog.show() // show progress dialog

        Api.getClient().getStories(object : retrofit.Callback<StoryResponse?> {
            override fun success(
                storyResponse: StoryResponse?,
                response: Response
            ) {
                progressDialog.dismiss() //dismiss progress dialog
                Log.i(TAG, "success: " + storyResponse?.data?.size)

                storyResponse?.data?.let { initViewPager(it) }
            }

            override fun failure(error: RetrofitError) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                Log.i(TAG, "failure: $error.toString()")
                progressDialog.dismiss() //dismiss progress dialog
            }

        })
    }



}