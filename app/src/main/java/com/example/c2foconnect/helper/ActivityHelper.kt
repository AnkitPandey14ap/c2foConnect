package com.example.c2foconnect.helper

import android.app.Activity
import android.content.Intent
import com.example.c2foconnect.LoginActivity
import com.example.c2foconnect.videos.VideoActivity


class ActivityHelper {
    companion object {
        public fun openHomeActivity(activity: Activity) {
            val intent = Intent(activity, VideoActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
        public fun openLoginActivity(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }
}