package com.example.c2foconnect.videos

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.c2foconnect.R
import com.example.c2foconnect.base.BaseActivity
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.video.model.UserBean
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        var user = BPreference.getUser(this);
        initView(user)
        initListners()
        addHomeFragment()
    }

    private fun addHomeFragment() {
        val simpleFragment: RootFragment? = RootFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
            .beginTransaction()

        simpleFragment?.let {
            fragmentTransaction.replace(
                R.id.fragmentContainer,
                it
            ).addToBackStack(null).commit()
        };
    }

    private fun addMyVideosFragment() {
        val simpleFragment: MyVideosFragment? = MyVideosFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
            .beginTransaction()

        simpleFragment?.let {
            fragmentTransaction.replace(
                R.id.fragmentContainer,
                it
            ).addToBackStack(null).commit()
        };
    }

    private fun initView(userBean: UserBean) {
        nameTV.text = userBean.name;

        Picasso.with(this).load(userBean.imageUrl)
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


    private fun initListners() {
        chatIV.setOnClickListener {
            ActivityHelper.openConnectionListActivity(this)
        }
        userIV.setOnClickListener {
            ActivityHelper.openProfileActivity(this)
        }

        homeTV.setOnClickListener {
            addHomeFragment()
            homeTV.setTextColor(ContextCompat.getColor(this, R.color.white));
            homeTV.setBackground(ContextCompat.getDrawable(this, R.drawable.corner_36_green));
            myVideosTV.setTextColor(ContextCompat.getColor(this, R.color.text_color));
            myVideosTV.setBackgroundResource(0)
        }
        myVideosTV.setOnClickListener {
            addMyVideosFragment()

            myVideosTV.setTextColor(ContextCompat.getColor(this, R.color.white));
            myVideosTV.setBackground(ContextCompat.getDrawable(this, R.drawable.corner_36_green));
            homeTV.setTextColor(ContextCompat.getColor(this, R.color.text_color));
            homeTV.setBackgroundResource(0)
        }
    }


    override fun onBackPressed() {
        finish();
        super.onBackPressed()
    }
}