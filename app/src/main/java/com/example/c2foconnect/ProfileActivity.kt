package com.example.c2foconnect

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.helper.BPreference
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initView()
    }

    private fun initView() {
        saveIV.setOnClickListener {
            ActivityHelper.openHomeActivity(this)
        }

        logoutTV.setOnClickListener {
            BPreference.logout(this)
//            ActivityHelper.openSplashActivity(this)
            triggerRebirth(this)


        }
    }

    fun triggerRebirth(context: Context) {
        val intent = Intent(context, SplashActivity::class.java)
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
//        intent.putExtra(KEY_RESTART_INTENT, nextIntent)
        context.startActivity(intent)
        if (context is Activity) {
            context.finish()
        }
        Runtime.getRuntime().exit(0)
    }

}