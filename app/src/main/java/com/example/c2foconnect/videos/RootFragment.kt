package com.example.c2foconnect.videos

import android.app.Activity
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import com.example.c2foconnect.R
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.video.model.StoriesDataModel
import com.example.c2foconnect.video.model.UserBean
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_root.*


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

        var user = UserBean();

        initView(user)
        initListners()

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
        viewPager2.adapter = ViewPagerFragmentStateAdapter(this, list)
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

    private fun initListners() {
        chatIV.setOnClickListener({
            ActivityHelper.openConnectionListActivity(context as Activity)
        })
    }

    private fun initView(userBean: UserBean) {
        nameTV.text = userBean.name;

        Picasso.with(context).load(userBean.imageUrl)
            .resize(48, 48)
            .into(userIV, object : Callback {
                override fun onSuccess() {
                    val imageBitmap =
                        (userIV.drawable as BitmapDrawable).bitmap
                    val imageDrawable =
                        RoundedBitmapDrawableFactory.create(resources, imageBitmap)
                    imageDrawable.isCircular = true
                    imageDrawable.cornerRadius = Math.max(
                        imageBitmap.width,
                        imageBitmap.height
                    ) / 2.0f
                    userIV.setImageDrawable(imageDrawable)
                }

                override fun onError() {
                    userIV.setImageResource(R.drawable.ic_follow_avatar_bottom_icon)
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


}