package com.example.c2foconnect.videos

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.c2foconnect.R
import com.example.c2foconnect.base.BaseActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.Util


class VideoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val simpleFragment: RootFragment? = RootFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
            .beginTransaction()

        simpleFragment?.let {
            fragmentTransaction.add(
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