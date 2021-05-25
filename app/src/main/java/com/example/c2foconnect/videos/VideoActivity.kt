package com.example.c2foconnect.videos

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.c2foconnect.R
import com.example.c2foconnect.base.BaseActivity
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.helper.ImageHelper
import com.example.c2foconnect.video.model.User
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        var user = BPreference.getUser(this);
        if (user != null) {
            initView(user)
            initListners()
            addHomeFragment()
        }
    }

    private fun initView(userBean: User) {
        nameTV.text = userBean.name;
        ImageHelper.setRoundImage(this, userIV, userBean)
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


    override fun onBackPressed() {
        finish();
        super.onBackPressed()
    }
}