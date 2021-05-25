package com.example.c2foconnect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.helper.BPreference

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (BPreference.isLogedIn(this)) {
            ActivityHelper.openHomeActivity(this)
//            ActivityHelper.openChatActivity(this)
        } else {
            ActivityHelper.openLoginActivity(this)
        }
    }
}