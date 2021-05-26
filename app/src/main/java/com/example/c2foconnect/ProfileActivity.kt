package com.example.c2foconnect

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.helper.ImageHelper
import com.example.c2foconnect.video.model.User
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        user = BPreference.getUser(this)
        if (user != null) {
            initView()
        }
        initListeners()
    }

    private fun initView() {
        var user=this.user
        if (user == null)
            return
        ImageHelper.setRoundImage(this, userIV, user.profileImageUrl, 60)
        nameET.setText(user.name)
        detailET.setText(user.companyDetails)
        phoneET.setText(user.phone)
        emailET.setText(user.email)
        sectorET.setText(user.sector)
        buisinessTypeET.setText(user.businessType)
        geographicalET.setText(user.address)
//        invoiceToolET.setText(user.in)
    }

    private fun initListeners() {
        saveIV.setOnClickListener {
            ActivityHelper.openHomeActivity(this)
        }

        logoutTV.setOnClickListener {
            BPreference.logout(this)
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